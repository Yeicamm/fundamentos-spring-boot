package com.fundamentos.springboot.fundamentos.bean;

public class MyDependencyImplement implements MyDependency {
    @Override
    public int mult(int number) {
        return number*3;
    }
}
