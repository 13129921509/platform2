package com.cloud.shopserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cloud.publicmodel.entity.LoginUserEntity;
import com.cloud.publicmodel.entity.ShopHeaderEntity;
import com.cloud.publicmodel.entity.response.ErrorResponseBody;
import com.cloud.publicmodel.entity.response.Result;
import com.cloud.publicmodel.entity.response.SuccessResponseBody;
import com.cloud.publicmodel.session.HttpClient;
import com.cloud.shopserver.mapper.ShopMapper;
import com.cloud.shopserver.service.impl.ShopServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShopBisinessController {
    @Autowired
    ShopServiceImp serviceImp;

    @Autowired
    ShopMapper shopMapper;
    @RequestMapping(value = "/httpclient",method = RequestMethod.POST)
    public HttpClient getHttpClient(@RequestBody LoginUserEntity entity){
        return serviceImp.getHttpClient("HttpClient:"+entity.getEmail());

    }

    @RequestMapping(value = "/checkHealth",method = RequestMethod.POST)
    public String checkHealth(){
        return "123";
    }

    @RequestMapping(value = "/shop/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    public Result getShopById(@PathVariable("id") String id){
        LambdaQueryWrapper<ShopHeaderEntity> wrapper = new LambdaQueryWrapper<>();
        ShopHeaderEntity entity = shopMapper.getShopHeaderEntity(wrapper.eq(ShopHeaderEntity::getId,id));
        if (entity!=null){
            return new SuccessResponseBody("success",entity);
        }else{
            return new ErrorResponseBody("商家不存在",ErrorResponseBody.ErrorCode.SHOP_DOES_NOT_EXIST.getCode());
        }
    }
}
