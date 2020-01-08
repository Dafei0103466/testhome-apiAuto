package com.dafei.api;

import com.alibaba.fastjson.JSON;
import com.dafei.utils.AbstractApiModel;
import com.dafei.utils.ApiYmlModule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.restassured.response.Response;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@Component
public class BaseApi {
    @Autowired
    ApiYmlModule apiYmlModule;
    @Autowired
    AbstractApiModel abstractApiModel;

    public HashMap<String,Object> requestParams;
    public HashMap<String,Object> ymlParams = new HashMap<>(); //指定替换yml文件的变量值

    public Response parseSteps() {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());  //为YAML $创建一个ObjectMapper映射器b $ b
        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        String filePath = "/" + className.replace(".","/")+".yml";
        try {
//          apiYmlModule =  mapper.readValue(new File(filePath), apiYmlModule.getClass());
            apiYmlModule =  mapper.readValue(BaseApi.class.getResourceAsStream(filePath), apiYmlModule.getClass());
            abstractApiModel = apiYmlModule.getInterFace(methodName);
        }catch (Exception e){throw new RuntimeException("序列化异常"+e);}
        String ymlValue = null;
        try {
            ymlValue = mapper.writeValueAsString(abstractApiModel);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //回写模型类完成最终序列化
        try {
            abstractApiModel = mapper.readValue(replaceYmlParams(ymlValue),abstractApiModel.getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return abstractApiModel.request(requestParams);
    }
    public String replaceYmlParams(String ymlValue){
        //替换yml文件${xxxxx}变量的值
        for (Map.Entry entry:ymlParams.entrySet()){
            String pattern = "\\$\\{"+entry.getKey()+"}";
            Pattern r = Pattern.compile(pattern);
            Matcher matcher = r.matcher(ymlValue);
            while (matcher.find()){
                ymlValue = ymlValue.replace(matcher.group(),entry.getValue().toString());
            }
        }
        return ymlValue;
    }

//    public Response excuate(HashMap<String, Object> requestData){
//        abstractApiModel.setPostBody(requestData);//如果传参数则替换postbody
//        return excuate();
//    }

}
