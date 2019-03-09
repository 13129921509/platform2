package com.cloud.commodityserver.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cloud.publicmodel.entity.CommodityChildEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommodityChildMapper extends BaseMapper<CommodityChildEntity> {
    /**
     *
     * @param wrapper
     * @return 返回一个商品子项
     */
    @Select("select * from commodity_child ${ew.customSqlSegment}")
    CommodityChildEntity getCommodityChildEntityByCommodityId(@Param(Constants.WRAPPER)Wrapper wrapper);

    /**
     *
     * @param wrapper
     * @return 返回所有符合条件的商品子项
     */
    @Select("select * from commodity_child ${ew.customSqlSegment}")
    List<CommodityChildEntity> getCommodityChildEntity(@Param(Constants.WRAPPER)Wrapper wrapper);

}
