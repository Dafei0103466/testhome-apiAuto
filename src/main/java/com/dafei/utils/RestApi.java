package com.dafei.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestApi {
    private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(RestApi.class);

    public String josnFormat(Object object){
        return JSON.toJSONString(object, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
    }
    public Response restPost(String url, String token, Object requestBody) {
        log.info("请求参数如下------->>>>>>>>>>\n"+josnFormat(requestBody));
        log.info("请求地址------->>>>>>>>>>\n"+url);
        log.info("请求token------->>>>>>>>>>\n"+token);
        Response response =
                given() .queryParam("access_token", token)
                        .contentType(ContentType.JSON)
                        .body(requestBody).
                        when().
                            post(url).
                        then().
                        extract()
                            .response();
        log.info("服务端返回报文------------->\n"+josnFormat(response.body().print()));
        return response;
    }
    public Response restGet(String url, String token) {
        log.info("请求服务地址----------->>>>>>>>>\n"+url);
        Response response =
                given().
                        queryParam("access_token",token).
                        when().
                        get(url).
                        then().
                        extract().
                        response();
        log.info("服务端返回报文------------->\n"+josnFormat(response.body().print()));
        return  response;
    }

}
