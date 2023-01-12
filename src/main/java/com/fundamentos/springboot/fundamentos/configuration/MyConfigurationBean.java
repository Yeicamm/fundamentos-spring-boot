package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigurationBean {
    @Bean
    public MyBean beanOperation(){
        return new MyBean2Implement();
    }
    @Bean
    public MyOperation beanOperationOperation(){
        return new MyOperationImplement();
    }
    @Bean
    public MyBeanWhiteDependency beanOperationOperationWithDepen(MyOperation myOperation){
        return new MyBeanWithDependencyImplement(myOperation);
    }
    @Bean
    public MyDependency myDependency(){
        return new MyDependencyImplement();
    }
    @Bean
    public MyPrintImplement myPrintImplement(MyDependency myDependency){
        return new MyPrintImplement(myDependency);
    }
}

