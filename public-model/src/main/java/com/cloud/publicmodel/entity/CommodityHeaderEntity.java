package com.cloud.publicmodel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("commodity_header")
public class CommodityHeaderEntity {

    @TableField("commodityName")
    String commodityName;

    @TableField("introduce")
    String introduce;

    @TableField("productionDate")
    String productionDate;

    @TableField("weight")
    String weight;

    @TableField("packingList")
    String packingList;

    @TableField("brand")
    String brand;

    @TableField("model")
    String model;

    @TableField("mobileOperatingSystem")
    String mobileOperatingSystem;

    @TableField("systemVersion")
    String systemVersion;

    @TableField("cameraType")
    String cameraType;

    @TableField("frontCamera")
    String frontCamera;

    @TableField("rearCamera")
    String rearCamera;

    @TableField("numberOfRearCameras")
    String numberOfRearCameras;

    @TableField("photographingCharacteristics")
    String photographingCharacteristics;

    @TableField("screenSize")
    String screenSize;

    @TableField("screenResolution")
    String screenResolution;

    @TableField("screenMaterial")
    String screenMaterial;

    @TableField("fuselageLength")
    String fuselageLength;

    @TableField("machineWidth")
    String machineWidth;

    @TableField("fuselageThickness")
    String fuselageThickness;

    @TableField("CPUBbrand")
    String CPUBbrand;

    @TableField("CPUFrequency")
    String CPUFrequency;

    @TableField("CPUModel")
    String CPUModel;

    @TableField("SIMCardSize")
    String SIMCardSize;

    @TableField("4GNetworkSystem")
    String _4GNetworkSystem;

    @TableField("3GNetworkSystem")
    String _3GNetworkSystem;

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPackingList() {
        return packingList;
    }

    public void setPackingList(String packingList) {
        this.packingList = packingList;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMobileOperatingSystem() {
        return mobileOperatingSystem;
    }

    public void setMobileOperatingSystem(String mobileOperatingSystem) {
        this.mobileOperatingSystem = mobileOperatingSystem;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getCameraType() {
        return cameraType;
    }

    public void setCameraType(String cameraType) {
        this.cameraType = cameraType;
    }

    public String getFrontCamera() {
        return frontCamera;
    }

    public void setFrontCamera(String frontCamera) {
        this.frontCamera = frontCamera;
    }

    public String getRearCamera() {
        return rearCamera;
    }

    public void setRearCamera(String rearCamera) {
        this.rearCamera = rearCamera;
    }

    public String getNumberOfRearCameras() {
        return numberOfRearCameras;
    }

    public void setNumberOfRearCameras(String numberOfRearCameras) {
        this.numberOfRearCameras = numberOfRearCameras;
    }

    public String getPhotographingCharacteristics() {
        return photographingCharacteristics;
    }

    public void setPhotographingCharacteristics(String photographingCharacteristics) {
        this.photographingCharacteristics = photographingCharacteristics;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
    }

    public String getScreenMaterial() {
        return screenMaterial;
    }

    public void setScreenMaterial(String screenMaterial) {
        this.screenMaterial = screenMaterial;
    }

    public String getFuselageLength() {
        return fuselageLength;
    }

    public void setFuselageLength(String fuselageLength) {
        this.fuselageLength = fuselageLength;
    }

    public String getMachineWidth() {
        return machineWidth;
    }

    public void setMachineWidth(String machineWidth) {
        this.machineWidth = machineWidth;
    }

    public String getFuselageThickness() {
        return fuselageThickness;
    }

    public void setFuselageThickness(String fuselageThickness) {
        this.fuselageThickness = fuselageThickness;
    }

    public String getCPUBbrand() {
        return CPUBbrand;
    }

    public void setCPUBbrand(String CPUBbrand) {
        this.CPUBbrand = CPUBbrand;
    }

    public String getCPUFrequency() {
        return CPUFrequency;
    }

    public void setCPUFrequency(String CPUFrequency) {
        this.CPUFrequency = CPUFrequency;
    }

    public String getCPUModel() {
        return CPUModel;
    }

    public void setCPUModel(String CPUModel) {
        this.CPUModel = CPUModel;
    }

    public String getSIMCardSize() {
        return SIMCardSize;
    }

    public void setSIMCardSize(String SIMCardSize) {
        this.SIMCardSize = SIMCardSize;
    }

    public String get_4GNetworkSystem() {
        return _4GNetworkSystem;
    }

    public void set_4GNetworkSystem(String _4GNetworkSystem) {
        this._4GNetworkSystem = _4GNetworkSystem;
    }

    public String get_3GNetworkSystem() {
        return _3GNetworkSystem;
    }

    public void set_3GNetworkSystem(String _3GNetworkSystem) {
        this._3GNetworkSystem = _3GNetworkSystem;
    }

    public CommodityHeaderEntity(String commodityName, String introduce, String productionDate, String weight, String packingList, String brand, String model, String mobileOperatingSystem, String systemVersion, String cameraType, String frontCamera, String rearCamera, String numberOfRearCameras, String photographingCharacteristics, String screenSize, String screenResolution, String screenMaterial, String fuselageLength, String machineWidth, String fuselageThickness, String CPUBbrand, String CPUFrequency, String CPUModel, String SIMCardSize, String _4GNetworkSystem, String _3GNetworkSystem) {
        this.commodityName = commodityName;
        this.introduce = introduce;
        this.productionDate = productionDate;
        this.weight = weight;
        this.packingList = packingList;
        this.brand = brand;
        this.model = model;
        this.mobileOperatingSystem = mobileOperatingSystem;
        this.systemVersion = systemVersion;
        this.cameraType = cameraType;
        this.frontCamera = frontCamera;
        this.rearCamera = rearCamera;
        this.numberOfRearCameras = numberOfRearCameras;
        this.photographingCharacteristics = photographingCharacteristics;
        this.screenSize = screenSize;
        this.screenResolution = screenResolution;
        this.screenMaterial = screenMaterial;
        this.fuselageLength = fuselageLength;
        this.machineWidth = machineWidth;
        this.fuselageThickness = fuselageThickness;
        this.CPUBbrand = CPUBbrand;
        this.CPUFrequency = CPUFrequency;
        this.CPUModel = CPUModel;
        this.SIMCardSize = SIMCardSize;
        this._4GNetworkSystem = _4GNetworkSystem;
        this._3GNetworkSystem = _3GNetworkSystem;
    }

    public CommodityHeaderEntity(){}
}
