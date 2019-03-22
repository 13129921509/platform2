package com.cloud.commodityserver.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.commodityserver.mapper.CommodityChildMapper;
import com.cloud.commodityserver.mapper.CommodityImgMapper;
import com.cloud.commodityserver.mapper.CommodityMapper;
import com.cloud.publicmodel.client.RedisClient;
import com.cloud.publicmodel.entity.CommodityChildEntity;
import com.cloud.publicmodel.entity.CommodityHeaderEntity;
import com.cloud.publicmodel.entity.CommodityImgEntity;
import com.cloud.publicmodel.entity.response.ErrorResponseBody;
import com.cloud.publicmodel.entity.response.Result;
import com.cloud.publicmodel.entity.response.SuccessResponseBody;
import com.cloud.publicmodel.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    CommodityMapper commodityMapper;

    @Autowired
    RedisClient redisClient;
    /**
     * 通过child表中的commodityCode来获得header表中的id
     * 拿到id去img表中获得所有图片的信息
     * @param commodityCode
     * @return
     */
    @RequestMapping("/order/img/{commodityCode}")
    public List<String> getOrderListImg(@PathVariable("commodityCode") String commodityCode){
        LambdaQueryWrapper<CommodityChildEntity> childWrapper = new LambdaQueryWrapper<CommodityChildEntity>();
        LambdaQueryWrapper<CommodityImgEntity> imgEntity = new LambdaQueryWrapper<CommodityImgEntity>();

        CommodityChildEntity entity = commodityChildMapper.getCommodityChildEntityByCommodityId(childWrapper.eq(CommodityChildEntity::getCommodityCode,commodityCode));
        //获得图片地址
        List<String> list = commodityImgMapper.getImgSrc(imgEntity.eq(CommodityImgEntity::getRelationId,entity.getCommodityMainId()));
        return list;
    }

    /**
     * 拿到id去img表中获得所有图片的信息
     * @param commodityId
     * @return
     */
    @RequestMapping(value = "/img/{commodityId}",method = {RequestMethod.GET,RequestMethod.POST})
    public List<String> getOrderListImgById(@PathVariable("commodityId") String commodityId){
        LambdaQueryWrapper<CommodityImgEntity> imgEntity = new LambdaQueryWrapper<CommodityImgEntity>();
        //获得图片地址
        List<String> list = commodityImgMapper.getImgSrc(imgEntity.eq(CommodityImgEntity::getRelationId,commodityId));
        return list;
    }
    /**
     * 返回列表界面所需要的信息
     */

    @RequestMapping("/list/{index}")
    public Object getInfoOfListPage(@RequestParam(value = "brand",required = false) String brand, @PathVariable String index, @RequestBody Map<String,Object> map,HttpServletResponse response) throws InterruptedException {
        Map<String,String> childMap = null;
        ArrayList<Map> paramList = new ArrayList<>();//存放必须的搜索参数 做二次使用
        QueryWrapper<CommodityHeaderEntity> headerWrapper = new QueryWrapper<>();
        QueryWrapper<CommodityChildEntity> childWrapper = new QueryWrapper<>();
        Page<CommodityHeaderEntity> page = new Page<CommodityHeaderEntity>(Integer.valueOf(index), 10);
        //response.addCookie(new Cookie("page",String.valueOf(page.getCurrent())));
        //为了防止出现采用param方式提交属性的可能，进行判断
        if (brand!=null&&map.get("brand")==null){
            map.put("brand",brand);
        }
        if (map.get("version")!= null || map.get("color")!= null){
            childMap = new HashMap<>();
            if (map.get("version")!= null){
                childMap.put("version",(String) map.get("version"));
                map.remove("version");
            }
            if (map.get("color")!= null){
                childMap.put("color",(String) map.get("color"));
                map.remove("color");
            }
        }
        if (map.size() != 0){//指的是有header的条件
            if (childMap!=null&&childMap.size()!=0){
                List<CommodityHeaderEntity> commodityHeaderEntityIPage =  commodityMapper.getCommodityHeaderEntityIPage(
                        page.setCurrent(Integer.valueOf(index)),
                        headerWrapper.allEq(map));
                List<Integer> ids = new ArrayList<>();
                for (CommodityHeaderEntity entity : commodityHeaderEntityIPage){
                    ids.add(entity.getId());
                }
                List<CommodityChildEntity> commodityChildEntities = commodityChildMapper.getCommodityChildEntity(
                        childWrapper.allEq(childMap).in("commodityMainId",ids)
                );
                List<CommodityHeaderEntity> removeCommodityHeaderEntities = new ArrayList<>();
                boolean sing = false;
                for (CommodityHeaderEntity commodityHeaderEntity:commodityHeaderEntityIPage){
                    sing =false;
                    for (CommodityChildEntity childEntity : commodityChildEntities){
                        if (childEntity.getCommodityMainId().equals(String.valueOf(commodityHeaderEntity.getId()))){
                            sing = true;
                            break;
                        }
                    }
                    if (sing != true){
                        removeCommodityHeaderEntities.add(commodityHeaderEntity);
                    }
                }
                for (int i=0 ; i<  removeCommodityHeaderEntities.size();i++){
                    commodityHeaderEntityIPage.remove(removeCommodityHeaderEntities.get(i));
                }
                System.out.printf(String.valueOf(page.hasNext()));
                if (commodityChildEntities.size() == 0){
                    //假如子选项为空 那么必定父选项为空
                    paramList.add(map);
                    paramList.add(childMap);
                    addParams(paramList,response);
                    return null;
                }
                System.out.printf(String.valueOf(page.hasNext()));
                paramList.add(map);
                paramList.add(childMap);
                return commodityHeaderEntityIPage;
            }//包含了child的条件和header的条件
            //指的是没有child的条件
            List<CommodityHeaderEntity> commodityHeaderEntityIPage =  commodityMapper.getCommodityHeaderEntityIPage(
                    page.setCurrent(Integer.valueOf(index)),
                    headerWrapper.allEq(map)
            );

            System.out.printf(String.valueOf(page.hasNext()));
            paramList.add(map);
            paramList.add(childMap);
            addParams(paramList,response);
            return commodityHeaderEntityIPage;
        }




        //指的是不含header的条件
        if (childMap!=null&&childMap.size()!=0 && (childMap.get("version")!= null || childMap.get("color")!= null)){
            List<CommodityHeaderEntity> commodityHeaderEntityIPage =  commodityMapper.getCommodityHeaderEntityIPage(
                    page.setCurrent(Integer.valueOf(index)),
                    headerWrapper);
            List<Integer> ids = new ArrayList<>();
            for (CommodityHeaderEntity entity : commodityHeaderEntityIPage){
                ids.add(entity.getId());
            }
            List<CommodityChildEntity> commodityChildEntities = commodityChildMapper.getCommodityChildEntity(
                    childWrapper.allEq(childMap).in("commodityMainId",ids)
            );
            List<CommodityHeaderEntity> removeCommodityHeaderEntities = new ArrayList<>();
            boolean sing = false;
            for (CommodityHeaderEntity commodityHeaderEntity:commodityHeaderEntityIPage){
                sing =false;
                for (CommodityChildEntity childEntity : commodityChildEntities){
                    if (childEntity.getCommodityMainId().equals(String.valueOf(commodityHeaderEntity.getId()))){
                        sing = true;
                        break;
                    }
                }
                if (sing != true){
                    removeCommodityHeaderEntities.add(commodityHeaderEntity);
                }
            }
            for (int i=0 ; i<  removeCommodityHeaderEntities.size();i++){
                commodityHeaderEntityIPage.remove(removeCommodityHeaderEntities.get(i));
            }
            System.out.printf(String.valueOf(page.hasNext()));
            paramList.add(map);
            paramList.add(childMap);
            addParams(paramList,response);
            return commodityHeaderEntityIPage;
        }else{
            List<CommodityHeaderEntity> commodityHeaderEntityIPage =  commodityMapper.getCommodityHeaderEntityIPage(
                    page.setCurrent(Integer.valueOf(index)),
                    headerWrapper
            );

            System.out.printf(String.valueOf(page.hasNext()));
            paramList.add(map);
            paramList.add(childMap);
            addParams(paramList,response);
            return commodityHeaderEntityIPage;
        }
    }

    /**
     * 将参数保存至redis'中 做备用
     * key 为随机生成的uuid 保存在cookie中
     * Params:uuid
     */
    public void addParams(ArrayList<Map> list,HttpServletResponse response) throws InterruptedException {
        String key = FileUtil.getUniqueCode();
        response.addCookie(new Cookie("paramsCode",key));
        redisClient.setObjectOfString("Params:"+key,list);
    }

    /**
     * 通过id去获取填充的信息
     */
    @RequestMapping(value = "/commodity/{id}",method = {RequestMethod.POST,RequestMethod.GET})
    public String getCommodityHeaderEntityById(@PathVariable("id") String id){
        LambdaQueryWrapper<CommodityHeaderEntity> wrapper = new LambdaQueryWrapper<>();
        CommodityHeaderEntity entity =commodityMapper.getCommodityHeaderById(wrapper.eq(CommodityHeaderEntity::getId,id));
        if (entity!=null){
            String result = JSON.toJSONString(new SuccessResponseBody("success",entity));
            return result;
        }else{
            String result = JSON.toJSONString(new ErrorResponseBody("商品不存在",ErrorResponseBody.ErrorCode.COMMODITY_DOES_NOT_EXIST.getCode()));
            return result;
        }
    }

    /**
     * 通过关联ID获取版本信息的信息
     */
    @RequestMapping(value = "/relationId/{id}",method = {RequestMethod.POST,RequestMethod.GET})
    public String getCommodityChildEntityByRelationId(@PathVariable("id") String id){
        LambdaQueryWrapper<CommodityChildEntity> wrapper = new LambdaQueryWrapper<>();
        List<CommodityChildEntity> entity =  commodityChildMapper.getCommodityChildEntity(wrapper.eq(CommodityChildEntity::getCommodityMainId,id));
        if (entity!=null){
            String result = JSON.toJSONString(new SuccessResponseBody("success",entity));
            return result;
        }else{
            String result = JSON.toJSONString(new ErrorResponseBody("关联id失效",ErrorResponseBody.ErrorCode.RELATION_ID_DOES_NOT_EXIST.getCode()));
            return result;
        }
    }

}
