package com.dafei.api;

import com.dafei.conf.Config;
import com.dafei.utils.RestApi;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

@Component
public class FlagMannage {
    @Autowired
    private Config config;
    @Autowired
    private RestApi restApi;
    public void create(Map params){
        Response response= restApi.restPost(config.getHost()+"/cgi-bin/tag/create",config.getToken(),params);
        response.then().assertThat().body("errcode",equalTo(0));
    }
    public void update(Map params){
        Response  response= restApi.restPost(config.getHost()+"/cgi-bin/tag/update",config.getToken(),params);
        response.then().assertThat().body("errcode",equalTo(0));
    }
    public void delete(Object tagid){
        Response  response= restApi.restGet(config.getHost()+"/cgi-bin/tag/delete?tagid="+tagid,config.getToken());
        response.then().assertThat().body("errcode",equalTo(0));
    }
    public List getList(){
        Response response = restApi.restGet(config.getHost()+"/cgi-bin/tag/list",config.getToken());
        response.then().assertThat().body("errcode",equalTo(0));
        return response.path("taglist");
    }
    public void addMemberFlag(Map params){
        Response  response= restApi.restPost(config.getHost()+"/cgi-bin/tag/addtagusers",config.getToken(),params);
        response.then().assertThat().body("errcode",equalTo(0));
    }
}
