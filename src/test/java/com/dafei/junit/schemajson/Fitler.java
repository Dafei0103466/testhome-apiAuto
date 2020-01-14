package com.dafei.junit.schemajson;

import com.dafei.junit.Junit5BaseTest;
import io.restassured.builder.ResponseBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.util.Base64;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Fitler extends Junit5BaseTest {
    @Test
    public void  fitlerTest(){
        given().filter((req,res,ctx)->{
            Response resOrigin = ctx.next(req, res);
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes = new byte[0];
            try {
                bytes = decoder.decodeBuffer(resOrigin.body().asString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            String msg = new String(bytes);
            ResponseBuilder responseBuilder = new ResponseBuilder().clone(resOrigin);
            responseBuilder.setBody(msg);
            return responseBuilder.build();
        })
                .when().log().all().get("http://101.132.159.87:8080/user.json")
                .then()
                    .body("userid",equalTo("seveniruby"));
    }



}
