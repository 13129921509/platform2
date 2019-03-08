package com.cloud.staticresources.controller;

import com.cloud.publicmodel.entity.LoginUserEntity;
import com.cloud.publicmodel.entity.OrderEntity;
import com.cloud.publicmodel.entity.UserDetailsEntity;
import com.cloud.publicmodel.entity.response.ErrorResponseBody;
import com.cloud.publicmodel.entity.response.Result;
import com.cloud.publicmodel.entity.response.SuccessResponseBody;
import com.cloud.publicmodel.session.HttpClient;
import com.cloud.publicmodel.util.FileUtil;
import com.cloud.staticresources.remoteapi.BusinessRemoteApi;
import com.cloud.staticresources.remoteapi.CommodityRemoteApi;
import com.cloud.staticresources.remoteapi.OrderRemoteApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BusinessController {
    @Autowired
    BusinessRemoteApi businessRemoteApi;

    @Autowired
    OrderRemoteApi orderRemoteApi;

    @Autowired
    CommodityRemoteApi commodityRemoteApi;
    @PostMapping("/key")
    public HttpClient getClient(HttpServletRequest request){
        LoginUserEntity entity = (LoginUserEntity) request.getSession().getAttribute("entity");
        return businessRemoteApi.getHttpClient(entity);
    }

    /**
     * 获得当前entity所有的订单，根据email查询订单
     */
    @RequestMapping(value = "/order")
    public List<OrderEntity> getOrder(HttpServletRequest request){
        LoginUserEntity entity = (LoginUserEntity) request.getSession().getAttribute("entity");
        return orderRemoteApi.getOrdersList(entity.getEmail());
    }

    /**
     *
     * @param
     * @return 返回一个<"商品名称":"商品图片地址"></>
     *
     */
    @RequestMapping("/order/img")
    public String getOrderListImg(HttpServletRequest request,@RequestBody OrderEntity orderEntity) throws URISyntaxException {
        List<String> list = commodityRemoteApi.getOrderListImg(orderEntity.getCommodityCode());
        return list.get(0);
    }

    /**
     * 返回一个用户详细json
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/detail",method = RequestMethod.POST)
    public Result userDetailsEntity(HttpServletRequest request){
        LoginUserEntity entity = (LoginUserEntity) request.getSession().getAttribute("entity");
        if (entity != null){
            return new SuccessResponseBody("success",businessRemoteApi.userDetailsEntity(entity));
        }else{
            return new ErrorResponseBody("USER_DETAILS_UNDEFINED",ErrorResponseBody.ErrorCode.USER_DETAILS_UNDEFINED.getCode());
        }
//        return businessRemoteApi.userDetailsEntity(entity);
    }
}
