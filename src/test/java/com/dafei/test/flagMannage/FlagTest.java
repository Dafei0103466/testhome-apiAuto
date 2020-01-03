package com.dafei.test.flagMannage;

import com.alibaba.fastjson.JSONObject;
import com.dafei.api.FlagMannage;
import com.dafei.test.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class FlagTest extends BaseTest {
    @Autowired
    FlagMannage flagMannage;
    @Test(description = "标签创建",dependsOnMethods = {"deleteFlag"})
    public void flagCreate(){
        JSONObject create = new JSONObject();
        create.put("tagname","creatFlagTest");
        create.put("tagid",2);
        flagMannage.create(create);
        logger.info(">>>>>>>>>>>>>>---create flag success ----<<<<<<<<<<<");
    }
    @Test(description = "标签更新",dependsOnMethods = {"flagCreate"})
    public void flagUpdate(){
        Map create = new HashMap();
        create.put("tagname","updateFlagTest");
        create.put("tagid",1);
        flagMannage.update(create);
        logger.info(">>>>>>>>>>>>>>---update flag success ----<<<<<<<<<<<");
    }
    @Test(description = "标签删除")
    public void deleteFlag(){
        flagMannage.delete(2);
        logger.info(">>>>>>>>>>>>>>---delete flag success ----<<<<<<<<<<<");
    }
    @Test
    public void getFlagList(){
        logger.info(flagMannage.getList());
    }
    @Test
    public void addMemberFlagTest(){
        Map data = new HashMap();
        data.put("tagid",2);
        data.put("userlist", Arrays.asList(new String[]{"autoCreate"}));
        flagMannage.addMemberFlag(data);
    }
}
