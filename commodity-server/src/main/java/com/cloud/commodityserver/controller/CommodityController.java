package com.cloud.commodityserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cloud.commodityserver.mapper.CommodityChildMapper;
import com.cloud.publicmodel.entity.CommodityChildEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommodityController {
    /**
     * 获得订单列表中所需要的图片
     * @return: 返回一个地址
     */
    @Autowired
    CommodityChildMapper commodityChildMapper;
    @RequestMapping("/order/img/{commodityCode}")
    public String getOrderListImg(@PathVariable("commodityCode") String commodityCode){
        LambdaQueryWrapper<CommodityChildEntity> wrapper = new LambdaQueryWrapper<CommodityChildEntity>();
        CommodityChildEntity entity = commodityChildMapper.getCommodityChildEntityByCommodityId(wrapper.eq(CommodityChildEntity::getCommodityCode,commodityCode));
        return entity.getImgDir();
    }
}
