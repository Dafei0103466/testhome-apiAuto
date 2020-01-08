package com.dafei.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
@Component
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AbstractApiModel {
    private String method;
    public HashMap<String,Object> query = new HashMap<>(); //
    private HashMap<String,Object> header;
    private HashMap<String,Object>  postBody;
    private String requestRaw; //对postBody参数传递的补充
    private String url;
    private String host;
    //必须实现请求方法
    public Response request() {
        RequestSpecification request = given();
        if (query!=null){
            query.entrySet().forEach(entry->request.queryParams(entry.getKey(),entry.getValue()));
        }
        if (header!=null){
            header.entrySet().forEach(entry->request.header(entry.getKey(),entry.getValue()));
        }
        //简单的参数直接通过yml文件传递
        if (postBody!=null){
            request.body(postBody);
        }
        //再添加一个入口可接受外部参数传递
        if (requestRaw!=null){
            request.body(requestRaw);
        }
        return request.
                when().log().all().request(method,host+url).
                then().log().all().extract().response();
    }
    //对postboby请求参数保存可覆盖的方法
    public Response request(HashMap requestBody) {
        if (requestBody!=null){
            postBody = requestBody;
        }
        return  request();
    }

    }
