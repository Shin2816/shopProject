package com.green.Shop.test;

import org.springframework.stereotype.Service;

// @Service("test") ==>> TestService test = new TestService();
@Service
public class TestService {

    public void test(){
        System.out.println("test 메소드 실행");
    }

}
