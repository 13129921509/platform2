package com.cloud.staticresources.controller;

import com.cloud.publicmodel.client.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ForwardController {

    @Autowired
    RedisClient redisClient;
    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/forward/{str}",method = {RequestMethod.POST,RequestMethod.GET})
    public String forward(HttpServletRequest request, @PathVariable String str,
                          @RequestParam(value = "brand",required = false) String brand,
                          @RequestParam(value = "page",required = false,defaultValue = "1") String page,
                          @RequestParam(value = "id",required = false,defaultValue = "1") String id
    ){
        //希望带参数的进行转发 比如带page brand
        //有时候转发可能带一些参数 这里进行判断
        //存储至session中
        Map map = new HashMap();
        if(str.equals("liebiao")){

            map.put("brand",brand);
            map.put("page",page);
            request.getSession().setAttribute("forward",map);
        }else if (str.equals("xiangqing")){
            map.put("id",id);
            request.getSession().setAttribute("forward",map);
        }
        return str;
    }

    ///forward/list?page=1&brand=mi
}
