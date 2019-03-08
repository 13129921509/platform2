package com.cloud.commodityserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cloud.commodityserver.mapper.CommodityChildMapper;
import com.cloud.commodityserver.mapper.CommodityImgMapper;
import com.cloud.publicmodel.entity.CommodityChildEntity;
import com.cloud.publicmodel.entity.CommodityImgEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommodityController {
    /**
     * 获得订单列表中所需要的图片
     * @return: 返回地址
     */
    @Autowired
    CommodityChildMapper commodityChildMapper;

    @Autowired
    CommodityImgMapper commodityImgMapper;
    @RequestMapping("/order/img/{commodityCode}")
    public List<String> getOrderListImg(@PathVariable("commodityCode") String commodityCode){
        LambdaQueryWrapper<CommodityChildEntity> childWrapper = new LambdaQueryWrapper<CommodityChildEntity>();
        LambdaQueryWrapper<CommodityImgEntity> imgEntity = new LambdaQueryWrapper<CommodityImgEntity>();

        CommodityChildEntity entity = commodityChildMapper.getCommodityChildEntityByCommodityId(childWrapper.eq(CommodityChildEntity::getCommodityCode,commodityCode));
        //获得图片地址
        List<String> list = commodityImgMapper.getImgSrc(imgEntity.eq(CommodityImgEntity::getRelationId,entity.getCommodityMainId()));
        return list;
    }
}
