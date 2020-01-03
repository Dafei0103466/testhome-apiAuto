package com.dafei.api;

import com.alibaba.fastjson.JSONObject;
import com.dafei.conf.Config;
import com.dafei.utils.RestApi;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

@Component
public class Member {
    @Autowired
    private Config config;
    @Autowired
    private RestApi restApi;
    public void create(JSONObject prams){
        Response response= restApi.restPost(config.getHost()+"/cgi-bin/user/create",config.getToken(),prams);
        response.then().assertThat().body("errcode",equalTo(0));
    }
    public void update(JSONObject prams){
        Response  response= restApi.restPost(config.getHost()+"/cgi-bin/user/update",config.getToken(),prams);
        response.then().assertThat().body("errcode",equalTo(0));
    }
    public void delete(Object userid){
        Response  response= restApi.restGet(config.getHost()+"/cgi-bin/user/delete?userid="+userid,config.getToken());
        response.then().assertThat().body("errcode",equalTo(0));
    }
    public void getMember(Object userid){
        Response  response= restApi.restGet(config.getHost()+"/cgi-bin/user/get?userid="+userid,config.getToken());
        response.then().assertThat().body("errcode",equalTo(0));
    }
    public List getList(){
        Response response = restApi.restGet(config.getHost()+"/cgi-bin/user/list",config.getToken());
        response.then().assertThat().body("errcode",equalTo(0));
        return response.path("taglist");
    }
}
