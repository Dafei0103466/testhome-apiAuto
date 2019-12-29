package com.dafei.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

public class GetTemplateDate {
    public  JSONObject getTemplateData(int id){
        return strToJson(getSourceData(id));
    }
    private String getSourceData(int id){
        return
                given().
                        queryParam("id",id).
                        when().
                        get("https://work.weixin.qq.com/api/docFetch/fetchCnt").
                        then().
                        extract().response().path("data.document.content_html");
    }
    private JSONObject strToJson(String line){
//        String line = GetTemplateDate.getTemplateData(10018);
        String pattern = "\\{[^%]*}\\n}";
        Pattern r = Pattern.compile(pattern);
        // 创建 matcher 对象
        Matcher m = r.matcher(line);
        String data = "";
        while (m.find()){
//            logger.info(m.group().replaceAll("&quot;","\\\""));
            data = m.group().replaceAll("&quot;","\\\"");
        }
        JSONObject jsonObject = JSONObject.parseObject(data);
        return  jsonObject;
    }

}
