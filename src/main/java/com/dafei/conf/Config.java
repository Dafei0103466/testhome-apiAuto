package com.dafei.conf;

import com.dafei.utils.GetTemplateDate;
import com.dafei.utils.RestApi;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.PostConstruct;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
@Data
@ContextConfiguration
@ComponentScan(basePackages = {"com.dafei.*"})
@PropertySource(value = "config.properties")
public class Config {

    @Value("${app.host}")
    private String host;
    @Value("${app.pid}")
    private String pid;
    @Value("${app.sercet}")
    private String sercet;
    private String token;

    @Bean
    public RestApi restApi(){
        return new RestApi();
    }
    @Bean
    public GetTemplateDate getTemplateDate(){
        return new GetTemplateDate();
    }

    @PostConstruct
    public void initToken(){
         token = given().
                        queryParams("corpid",pid,"corpsecret",sercet).
                when().
                        get(host+"/cgi-bin/gettoken").
                then().
                        body("errmsg",equalTo("ok")).
                extract().
                        path("access_token");
    }
}
