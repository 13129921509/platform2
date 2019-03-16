package com.cloud.commodityserver.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.publicmodel.entity.CommodityChildEntity;
import com.cloud.publicmodel.entity.CommodityHeaderEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 */
@Component
public interface CommodityMapper extends BaseMapper<CommodityHeaderEntity> {
    @Select("SELECT * from commodity_header ${ew.customSqlSegment}")
    List<CommodityHeaderEntity> getCommodityHeaderEntityIPage(Page<CommodityHeaderEntity> page, @Param("ew") QueryWrapper<CommodityHeaderEntity> headerWrappers);

    @Select("SELECT * from commodity_header ${ew.customSqlSegment}")
    CommodityHeaderEntity getCommodityHeaderById(@Param(Constants.WRAPPER) Wrapper<CommodityHeaderEntity> headerWrappers);

}
