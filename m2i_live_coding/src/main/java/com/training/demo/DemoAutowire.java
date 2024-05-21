package com.training.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DemoAutowire {

    @Value("${server.port}")
    private String myValue;

    public DemoAutowire() {
        System.out.println("DemoAutowire");
    }

    public String getMyValue() {
        return myValue;
    }

    public void setMyValue(String myValue) {
        this.myValue = myValue;
    }
}
