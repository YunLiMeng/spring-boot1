package com.example.demo.test;

import java.util.Arrays;

/**
 * @ author: limeng
 * @ date: Created in 2019/9/2 14:37
 * @ description：日常测试
 */
public class MyDayTimeTest {
    public static void main(String[] args) {
        try {
            overrideException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Arrays.stream(e.getSuppressed())
                    .map(Throwable::getMessage)
                    .forEach(System.out::println);
        }
    }

    public static  void overrideException() throws Exception {
        Exception catchException = null;
        try {
            throw new Exception("A");
        } catch (Exception e) {
            catchException = e;
        } finally {
            Exception exception = new Exception("C");
            exception.addSuppressed(catchException);
            throw exception;
        }
    }
}
