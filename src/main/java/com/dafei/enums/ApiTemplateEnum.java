package com.dafei.enums;

public enum ApiTemplateEnum {
    CREATE_MEMBER(10018,"创建成员接口地址"),
    UPDATE_MEMBER(10020,"更新成员接口地址"),
    INCREMENTAL_UPDATE_MEMBER(15014,"增量更新成员");

    private  final  int id;
    private  final  String name;
    private ApiTemplateEnum(int id ,String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
