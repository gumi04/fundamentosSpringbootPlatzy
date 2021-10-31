package com.fundamentos.springboot.fundamentos.bean;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

public class MyBeanWhitDependecyImpl implements MyBeanWhitDependecy{

    private Log LOGGER = LogFactory.getLog(MyBeanWhitDependecyImpl.class);
    private MyOperation myOperation;


    public MyBeanWhitDependecyImpl(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWhitDependency() {
        LOGGER.info("Hemos ingresado el metodo pringWhitDependency");
        int numero=1;
        LOGGER.debug("EL numero enviado como parametro a la dependencia "+numero);
        System.out.println(myOperation.suma(numero));
        System.out.println("hola desde la implementacion de un bean con dependencia");
    }
}
