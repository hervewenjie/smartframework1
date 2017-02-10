package com.herve.papa.controller;

import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chengwenjie on 2017/2/6.
 */
@Controller
public class TestController {

    @Action("get:/testdata")
    public Data testData(Param param){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("testtest", "googdgood");
        result.put("kaka", "papapa");
        result.put("code", "0");
        return new Data(result);
    }

    @Action("get:/testview")
    public View testView(Param param){
        return new View("hello.jsp").addModel("test", "papa");
    }
}
