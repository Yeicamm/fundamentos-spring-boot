package com.fundamentos.springboot.fundamentos.bean;

public class MyPrintImplement implements MyPrint{
    MyDependency mydependency;

    public MyPrintImplement(MyDependency mydependency){
        this.mydependency = mydependency;
    }
    @Override
    public void printImplement() {
        int number = 5;
        System.out.println("Este es el resultado de la multiplicacion");
        System.out.println(mydependency.mult(number));

    }
}
