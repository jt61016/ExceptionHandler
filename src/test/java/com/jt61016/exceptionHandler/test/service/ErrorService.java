package com.jt61016.exceptionHandler.test.service;

import org.springframework.stereotype.Component;

/**
 * @Author jiangtao
 * @Date 2018/8/26 下午3:43.
 */
@Component
public class ErrorService {

    public void throwException() {
        System.out.println("access method ...");

        int a = 1;
        int b = 0;
        int c = a / b;

        System.out.println("over ...");
    }
}
