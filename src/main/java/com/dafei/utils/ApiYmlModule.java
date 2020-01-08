package com.dafei.utils;


import com.dafei.conf.Config;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.given;


@Component
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiYmlModule {
    /*
        解析steps
        读取yml文件
     */
    public HashMap<String, AbstractApiModel> interfaces = new HashMap<>();
    public AbstractApiModel getInterFace(String methodName){
        return interfaces.get(methodName);
    }

}
