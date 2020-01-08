package com.dafei.junit;

import com.dafei.api.BaseApi;
import com.dafei.conf.Config;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.boot.test.context.SpringBootTest;



@Slf4j
@SpringBootTest(classes = {Config.class})
@ExtendWith(SpringExtension.class)

public class junit5BaseTest {

    @Autowired
    private Config config;
    @Autowired
    BaseApi baseApi;
    @BeforeEach
    public void initParams(){
        //初始化token和host
        baseApi.ymlParams.put("host",config.getHost());
        baseApi.ymlParams.put("access_token",config.getToken());
        log.info("host+token初始化完成");
    }
    @AfterEach
    public void cleanParams(){
        //请求参数清洗
        baseApi.ymlParams.put("requestRaw",null);
    }
}
