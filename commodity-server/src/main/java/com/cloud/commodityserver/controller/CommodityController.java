package com.cloud.commodityserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.commodityserver.mapper.CommodityChildMapper;
import com.cloud.commodityserver.mapper.CommodityImgMapper;
import com.cloud.commodityserver.mapper.CommodityMapper;
import com.cloud.publicmodel.entity.CommodityChildEntity;
import com.cloud.publicmodel.entity.CommodityHeaderEntity;
import com.cloud.publicmodel.entity.CommodityImgEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 返回列表界面所需要的信息
     */

    @RequestMapping("/list/{index}")
    public Object getInfoOfListPage(HttpServletResponse response,HttpServletRequest request, @PathVariable String index, @RequestBody Map<String,Object> map){
        Map<String,String> childMap = null;
        QueryWrapper<CommodityHeaderEntity> headerWrapper = new QueryWrapper<>();
        QueryWrapper<CommodityChildEntity> childWrapper = new QueryWrapper<>();
        Page<CommodityHeaderEntity> page = new Page<CommodityHeaderEntity>(Integer.valueOf(index), 5);
        //response.addCookie(new Cookie("page",String.valueOf(page.getCurrent())));
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
                    return null;
                }
                System.out.printf(String.valueOf(page.hasNext()));
                return commodityHeaderEntityIPage;
            }//包含了child的条件和header的条件
            //指的是没有child的条件
            List<CommodityHeaderEntity> commodityHeaderEntityIPage =  commodityMapper.getCommodityHeaderEntityIPage(
                    page.setCurrent(Integer.valueOf(index)),
                    headerWrapper.allEq(map)
            );

            System.out.printf(String.valueOf(page.hasNext()));
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
            return commodityHeaderEntityIPage;
        }else{
            List<CommodityHeaderEntity> commodityHeaderEntityIPage =  commodityMapper.getCommodityHeaderEntityIPage(
                    page.setCurrent(Integer.valueOf(index)),
                    headerWrapper
            );

            System.out.printf(String.valueOf(page.hasNext()));
            return commodityHeaderEntityIPage;
        }
    }

}
