package com.cloud.staticresources.controller;

import com.cloud.publicmodel.client.RedisClient;
import com.cloud.publicmodel.entity.LoginUserEntity;
import com.cloud.publicmodel.entity.OrderDetailsEntity;
import com.cloud.publicmodel.entity.OrderEntity;
import com.cloud.publicmodel.entity.response.ErrorResponseBody;
import com.cloud.publicmodel.entity.response.Result;
import com.cloud.publicmodel.entity.response.SuccessResponseBody;
import com.cloud.publicmodel.session.HttpClient;
import com.cloud.staticresources.remoteapi.BusinessRemoteApi;
import com.cloud.staticresources.remoteapi.CommodityRemoteApi;
import com.cloud.staticresources.remoteapi.OrderRemoteApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class BusinessController {
    @Autowired
    BusinessRemoteApi businessRemoteApi;

    @Autowired
    OrderRemoteApi orderRemoteApi;

    @Autowired
    CommodityRemoteApi commodityRemoteApi;

    @Autowired
    RedisClient redisClient;

    @Autowired
    ForwardController forwardController;

    /**
     * 获得用户独有的key，可保持并发操作的安全性
     * @param request
     * @return
     */
    @PostMapping("/key")
    public HttpClient getClient(HttpServletRequest request) {
        LoginUserEntity entity = (LoginUserEntity) request.getSession().getAttribute("entity");
        return businessRemoteApi.getHttpClient(entity);
    }

    /**
     * 获得当前entity所有的订单，根据email查询订单
     */
    @RequestMapping(value = "/order")
    public List<OrderEntity> getOrder(HttpServletRequest request) {
        LoginUserEntity entity = (LoginUserEntity) request.getSession().getAttribute("entity");
        return orderRemoteApi.getOrdersList(entity.getEmail());
    }

    /**
     * @param
     * @return 返回一个<" 商品名称 " : " 商品图片地址 "></>
     */
    @RequestMapping("/order/img")
    public String getOrderListImg(HttpServletRequest request, @RequestBody OrderEntity orderEntity) throws URISyntaxException {
        List<String> list = commodityRemoteApi.getOrderListImg(orderEntity.getCommodityCode());
        return list.get(0);
    }

    /**
     * 返回一个用户详细json
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/detail", method = RequestMethod.POST)
    public Result userDetailsEntity(HttpServletRequest request) {
        LoginUserEntity entity = (LoginUserEntity) request.getSession().getAttribute("entity");
        if (entity != null) {
            return new SuccessResponseBody("success", businessRemoteApi.userDetailsEntity(entity));
        } else {
            return new ErrorResponseBody("USER_DETAILS_UNDEFINED", ErrorResponseBody.ErrorCode.USER_DETAILS_UNDEFINED.getCode());
        }
//        return businessRemoteApi.userDetailsEntity(entity);
    }

    /**
     * 返回一个订单所有明细
     * @param request
     * @return
     * @throws InterruptedException
     */
    @RequestMapping(value = "/orderDetail",method = RequestMethod.POST)
    public Result getOrderDetailsEntities(HttpServletRequest request,HttpServletResponse response) throws InterruptedException, ServletException, IOException {
        LoginUserEntity entity = (LoginUserEntity) request.getSession().getAttribute("entity");
        String orderCode = (String) redisClient.getObjectOfString("OrderCode:"+entity.getYzm());
        if (orderCode == null){
            request.getRequestDispatcher("login.html").forward(request,response);
//            return new ErrorResponseBody("登陆超时",ErrorResponseBody.ErrorCode.LOGIN_TIMEOUT.getCode());
        }
        return new SuccessResponseBody("success",orderRemoteApi.getOrderDetailsEntities(orderCode));
    }

    /**
     * 在转发的之前进行存储参数，采用redis，其实可以使用拦截器或aop做操作
     * 后期修改
     * @param request
     * @param response
     * @throws InterruptedException
     * @throws ServletException
     * @throws IOException
     */
    @GetMapping("/orderCode")
    public void orderCode(HttpServletRequest request, HttpServletResponse response) throws InterruptedException, ServletException, IOException {
        LoginUserEntity entity = (LoginUserEntity) request.getSession().getAttribute("entity");
        redisClient.setObjectOfString("OrderCode:"+entity.getYzm(),request.getParameter("code"));
        request.getRequestDispatcher("dingdanxiangqing.html").forward(request,response);
    }

    /**
     * 通过商品编号获得订单明细缩略图
     */
    @RequestMapping("/orderDetails/img/{imgstr}")
    public String getorderDetailsListImg(@PathVariable String imgstr) throws URISyntaxException {
        List<String> list = commodityRemoteApi.getOrderListImg(imgstr);
        return list.get(0);
    }
}
