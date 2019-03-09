package com.cloud.publicmodel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("commodity_child")
public class CommodityChildEntity {

    @TableField("commodityMainId")
    public String commodityMainId;

    @TableField("color")
    public String color;

    @TableField("version")
    public String version;

    @TableField("imgDeatilDir")
    public String imgDeatilDir;

    @TableField("imgDir")
    public String imgDir;

    @TableField("commodityCode")
    public String commodityCode;

    @TableField("price")
    public String price;

    public String getCommodityMainId() {
        return commodityMainId;
    }

    public void setCommodityMainId(String commodityMainId) {
        this.commodityMainId = commodityMainId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getImgDeatilDir() {
        return imgDeatilDir;
    }

    public void setImgDeatilDir(String imgDeatilDir) {
        this.imgDeatilDir = imgDeatilDir;
    }

    public String getImgDir() {
        return imgDir;
    }

    public void setImgDir(String imgDir) {
        this.imgDir = imgDir;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public CommodityChildEntity(String commodityMainId, String color, String version, String imgDeatilDir, String imgDir, String commodityCode, String price) {
        this.commodityMainId = commodityMainId;
        this.color = color;
        this.version = version;
        this.imgDeatilDir = imgDeatilDir;
        this.imgDir = imgDir;
        this.commodityCode = commodityCode;
        this.price = price;
    }

    public CommodityChildEntity(){}
}
