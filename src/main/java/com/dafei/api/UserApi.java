package com.dafei.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.dafei.enums.ApiTemplateEnum;
import com.dafei.utils.DataGenerator;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class UserApi {
    @Autowired
    BaseApi baseApi;
    @Autowired
    DataGenerator dataGenerator;
    public Response simplelist(int id) {
        baseApi.ymlParams.put("department_id",id);//key值需对应接口文档且变量名也需一致
        return baseApi.parseSteps();
    }
    public Response batchdelete(List userList) {
        baseApi.setRequestParams(new HashMap(){{put("useridlist",userList);}});
        return baseApi.parseSteps();
    }
    public Response create(Map<String,Object> updateData){
        JSONObject jsonObject =  dataGenerator.getTemplateData(ApiTemplateEnum.CREATE_MEMBER.getId());
        updateData.entrySet().forEach(entry-> JSONPath.set(jsonObject,"$."+entry.getKey(),entry.getValue()));
        baseApi.setRequestParams(JSON.parseObject(jsonObject.toJSONString(), HashMap.class));
        return baseApi.parseSteps();
    }
}
