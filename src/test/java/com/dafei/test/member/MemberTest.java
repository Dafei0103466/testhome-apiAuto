package com.dafei.test.member;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.dafei.api.Member;
import com.dafei.test.BaseTest;
import com.dafei.utils.GetTemplateDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.Arrays;

public class MemberTest extends BaseTest {
    @Autowired
    Member member;
    @Autowired
    GetTemplateDate getTemplateDate;
    @Test(description = "创建成员",dependsOnMethods = {"deleteMemberTest"})
    public void createTest(){
        JSONObject jsonObject =  getTemplateDate.getTemplateData(10018);//10018是创建用户模板数据
        JSONPath.set(jsonObject,"$.department", Arrays.asList(new int[] { 2,3 }));
        JSONPath.set(jsonObject,"$.external_profile.external_attr.2.miniprogram.title","文档第一个坑");//
        JSONPath.set(jsonObject,"$.userid","autoCreate");
        JSONPath.set(jsonObject,"$.mobile",18012345678L);//电话号码不能重复
        JSONPath.set(jsonObject,"$.email","autoTest@qq.com");//邮箱不能重复
        JSONPath.remove(jsonObject,"$.order");
        JSONPath.remove(jsonObject,"$.is_leader_in_dept");
        JSONPath.remove(jsonObject,"$.external_profile.external_corp_name");//第三个坑企业名称不合法
        JSONPath.remove(jsonObject,"$.avatar_mediaid");//第二个坑
        member.create(jsonObject);
    }
    @Test(dependsOnMethods = {"createTest"})
    private void getMemberTest(){
        member.getMember("autoCreate");
        logger.info("获取信息成功");
    }
    @Test(description = "更新成员",dependsOnMethods = {"createTest"})
    public void updateTest(){
        JSONObject jsonObject =  getTemplateDate.getTemplateData(10020);//10020是官方更新用户模板数据
        JSONPath.set(jsonObject,"$.department", Arrays.asList(new int[] { 2,3 }));
        JSONPath.set(jsonObject,"$.external_profile.external_attr.2.miniprogram.title","文档第一个坑");//
        JSONPath.set(jsonObject,"$.userid","autoCreate");
        JSONPath.set(jsonObject,"$.mobile",18012341234L);//电话号码不能重复
        JSONPath.set(jsonObject,"$.email","autoUpdateTest@qq.com");//邮箱不能重复
        JSONPath.remove(jsonObject,"$.order");
        JSONPath.remove(jsonObject,"$.is_leader_in_dept");
        JSONPath.remove(jsonObject,"$.external_profile.external_corp_name");//第三个坑企业名称不合法
        JSONPath.remove(jsonObject,"$.avatar_mediaid");//第二个坑
        member.update(jsonObject);
    }
    @Test(description = "删除用户")
    private void deleteMemberTest(){
        member.delete("autoCreate");
        logger.info("删除成员成功");
    }
}
