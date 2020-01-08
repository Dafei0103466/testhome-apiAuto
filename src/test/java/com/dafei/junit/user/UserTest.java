package com.dafei.junit.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dafei.api.UserApi;
import com.dafei.junit.junit5BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.params.provider.Arguments.arguments;


@Slf4j
public class UserTest extends junit5BaseTest {
    @Autowired
    UserApi pOuserApi;
    public ArrayList userList = new ArrayList();
    @Test
    void getSimpleListTest() throws Exception {
        pOuserApi.simplelist(2).then().body("errcode",equalTo(0));
    }
    @Order(2)
    @Test
    void batchDeleteTest(){
//        pOuserApi.batchdelete(Arrays.asList(new String[] {"auto2261","auto2268"})).then().body("errcode",equalTo(0));
       pOuserApi.batchdelete(userList);
    }
    @Order(1)
    @ParameterizedTest
    @MethodSource("getCreateYml")
    void createTest(Map<String,Object> update){
        update.entrySet().forEach(entry->log.info(entry.getKey()+"<<更新字段名>>-<<更新值>>"+entry.getValue()));
        update.put("userid","auto"+ String.valueOf(System.currentTimeMillis()).substring(7,11));
        update.put("email","auto"+ String.valueOf(System.currentTimeMillis()).substring(7,11)+"@testhome.com");
        pOuserApi.create(update).then().body("errcode",equalTo(0));
        userList.add("auto"+ String.valueOf(System.currentTimeMillis()).substring(7,11));
    }

    static Stream<Arguments> getCreateYml(){
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        List<Map> updataData = new ArrayList<Map>();
        try {
            updataData = objectMapper.readValue(new File("src/main/resources/com/dafei/api/testcase/UserApi.yml"), List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Arguments> updataStrem= new ArrayList<Arguments>();
        updataData.forEach(v->updataStrem.add(arguments(v)));
        return updataStrem.stream();
    }
}
