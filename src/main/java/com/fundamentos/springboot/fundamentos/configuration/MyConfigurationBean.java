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
        return new MyOperationImpl();
    }

    @Bean
    public MyBeanWhitDependecy beanOperationOperationWhitDependency(MyOperation myOperation){
        return  new MyBeanWhitDependecyImpl(myOperation);
    }
}
