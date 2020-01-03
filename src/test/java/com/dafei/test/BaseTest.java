package com.dafei.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeTest;


@ContextConfiguration(classes={com.dafei.conf.Config.class})
public class BaseTest extends AbstractTestNGSpringContextTests {
    @Value("${app.host}")
    public String host;
    @Value("${app.pid}")
    public String pid;
    @Value("${app.sercet}")
    public String sercet;
    @BeforeTest
    public void print(){
        logger.info("test start------");
        logger.debug("debug");
        logger.warn("warn");
        logger.error("error");

    }
}
