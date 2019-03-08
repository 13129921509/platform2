package com.cloud.commodityserver.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cloud.publicmodel.entity.CommodityImgEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommodityImgMapper extends BaseMapper<CommodityImgEntity> {
    @Select("select imgSrc from commodity_img ${ew.customSqlSegment}")
    List<String> getImgSrc(@Param(Constants.WRAPPER)Wrapper<CommodityImgEntity> wrapper);
}
