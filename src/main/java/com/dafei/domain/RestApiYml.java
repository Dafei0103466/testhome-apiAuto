package com.dafei.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "myprops")
@PropertySource(value = "classpath:com/dafei/domain/restModel.yml")
public class RestApiYml {
    private String simpleProp;
    private String[] arrayProps;
    private List<Map<String, String>> listProp1 = new ArrayList<>(); //接收prop1里面的属性值
    private List<String> listProp2 = new ArrayList<>(); //接收prop2里面的属性值
    private Map<String, String> mapProps = new HashMap<>(); //接收prop1里面的属性值

    public String getSimpleProp() {
        return simpleProp;
    }

    //String类型的一定需要setter来接收属性值；maps, collections, 和 arrays 不需要
    public void setSimpleProp(String simpleProp) {
        this.simpleProp = simpleProp;
    }

    public List<Map<String, String>> getListProp1() {
        return listProp1;
    }
    public List<String> getListProp2() {
        return listProp2;
    }

    public String[] getArrayProps() {
        return arrayProps;
    }

    public void setArrayProps(String[] arrayProps) {
        this.arrayProps = arrayProps;
    }

    public Map<String, String> getMapProps() {
        return mapProps;
    }

    public void setMapProps(Map<String, String> mapProps) {
        this.mapProps = mapProps;
    }
}

