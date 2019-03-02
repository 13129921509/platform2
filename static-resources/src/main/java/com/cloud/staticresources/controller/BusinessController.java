package com.cloud.staticresources.controller;

import com.cloud.publicmodel.entity.LoginUserEntity;
import com.cloud.publicmodel.entity.OrderEntity;
import com.cloud.publicmodel.session.HttpClient;
import com.cloud.publicmodel.util.FileUtil;
import com.cloud.staticresources.remoteapi.BusinessRemoteApi;
import com.cloud.staticresources.remoteapi.CommodityRemoteApi;
import com.cloud.staticresources.remoteapi.OrderRemoteApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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
     * @param request
     * @return 返回一个<"商品名称":"商品图片地址"></>
     * 未完成
     */
    @RequestMapping("/order/img")
    public Map getOrderListImg(HttpServletRequest request){
        List<OrderEntity> list = getOrder(request);
        Map<String,String> imgMap = new HashMap<String, String>();
        String realPath = request.getServletContext().getRealPath("/");
//        File[] files = FileUtil.getFilesNameByPath(realPath);
        for (OrderEntity entity : list){
            String src = commodityRemoteApi.getOrderListImg(entity.getCommodityCode());
            imgMap.put(entity.getCommodityName(),realPath+"/"+src);
        }
        return imgMap;
    }
}
