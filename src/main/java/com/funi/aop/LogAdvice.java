package com.funi.aop;

public class LogAdvice implements Advice {
    public void beforeAdvice() {
        System.out.println("start.............");
    }

    public void afterAdvice() {
        System.out.println("end................");
    }
}
