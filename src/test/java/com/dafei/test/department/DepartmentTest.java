package com.dafei.test.department;

import com.dafei.api.Department;
import com.dafei.test.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class DepartmentTest extends BaseTest {
    @Autowired
    Department department;
    @Test
    public void getDepartmentListTest(){
        department.getList();
    }
    @Test (dependsOnMethods = {"deleteTest"})
    public void createTest(){
        Map reqData = new HashMap();
        reqData.put("name","三级部门");
//        reqData.put("name_en","AUTO2");
        reqData.put("id",4);
        reqData.put("parentid",3);
        reqData.put("order",2);
        department.create(reqData);
    }
    @Test(dependsOnMethods = {"createTest"})
    public void updateTest(){
        department.update(new HashMap(){{put("name","二级部门");put("id",3);}});
    }
    @Test(description = "删除部门")
    public void deleteTest(){
        department.delete(3);
    }

}
