package com.cloud.staticresources.remoteapi;

import com.alibaba.fastjson.JSON;
import com.cloud.publicmodel.entity.CommodityChildEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Component
@FeignClient("commodity-server")
public interface CommodityRemoteApi {
    /**
     *
     * @param commodityCode
     * @return 返回一个关于商品id的所有图片地址
     */
    @RequestMapping("/order/img/{commodityCode}")
    List<String> getOrderListImg(@PathVariable("commodityCode") String commodityCode);

    /**
     * 返回列表界面所需要的信息
     */

    @RequestMapping(value = "/list/{index}",method = RequestMethod.POST)
    Object getInfoOfListPage(@RequestParam(value = "brand",required = false) String brand, @PathVariable(value = "index") String index, @RequestBody(required = false) Map<String,Object> map);

    /**
     * 拿到id去img表中获得所有图片的信息
     * @param commodityId
     * @return
     */
    @RequestMapping(value = "/img/{commodityId}",method = RequestMethod.POST)
    List<String> getOrderListImgById(@PathVariable("commodityId") String commodityId);

    /**
     * 通过id去获取填充的信息
     */
    @RequestMapping(value = "/commodity/{id}",method = RequestMethod.POST)
    String getCommodityHeaderEntityById(@PathVariable("id") String id);

    /**
     * 通过关联ID获取版本信息的信息
     */
    @RequestMapping(value = "/relationId/{id}",method = RequestMethod.POST)
    String getCommodityChildEntityByRelationId(@PathVariable("id") String id);

    /**
     * 通过商品Code获取版本信息的信息
     */
    @RequestMapping(value = "/shopCode/{shopCode}",method = RequestMethod.POST)
    CommodityChildEntity getCommodityChildEntityByShopCode(@PathVariable("shopCode") String shopCode);
}
