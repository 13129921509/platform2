package com.cloud.publicmodel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("commodity_img")
public class CommodityImgEntity {
    @TableField("imgSrc")
    String imSrc;

    @TableField("relationId")
    String relationId;

    @TableField("imgDir")
    String imgDir;

    public String getImSrc() {
        return imSrc;
    }

    public void setImSrc(String imSrc) {
        this.imSrc = imSrc;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getImgDir() {
        return imgDir;
    }

    public void setImgDir(String imgDir) {
        this.imgDir = imgDir;
    }

    public CommodityImgEntity(String imSrc, String relationId, String imgDir) {
        this.imSrc = imSrc;
        this.relationId = relationId;
        this.imgDir = imgDir;
    }

    public CommodityImgEntity(){}
}
