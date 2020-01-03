package com.dafei.api;

import com.dafei.conf.Config;
import com.dafei.utils.RestApi;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class Department {
    @Autowired
    private Config config;
    @Autowired
    private RestApi restApi;
    public void create(Map prams){
        Response  response= restApi.restPost(config.getHost()+"/cgi-bin/department/create",config.getToken(),prams);
        response.then().assertThat().body("errcode", Matchers.equalTo(0));
       }
    public void update(Map prams){
        Response  response= restApi.restPost(config.getHost()+"/cgi-bin/department/update",config.getToken(),prams);
        response.then().assertThat().body("errcode", Matchers.equalTo(0));
    }
    public void delete(Object id){
        Response  response= restApi.restGet(config.getHost()+"/cgi-bin/department/delete?id="+id,config.getToken());
        response.then().assertThat().body("errcode", Matchers.equalTo(0));
     }
     public List getList(){
         Response response = restApi.restGet(config.getHost()+"/cgi-bin/department/list",config.getToken());
         response.then().assertThat().body("errcode", Matchers.equalTo(0));
         return response.path("department");
     }
}
