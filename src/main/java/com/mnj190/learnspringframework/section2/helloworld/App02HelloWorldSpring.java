package com.mnj190.learnspringframework.section2.helloworld;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class App02HelloWorldSpring {
    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class)) {
            // 1. Launch a Spring context

            // 2. Configure ths things that we want Spring to manage

            System.out.println(context.getBean("name"));

            System.out.println(context.getBean("age"));

            System.out.println(context.getBean("person"));

            System.out.println(context.getBean("address2"));

            System.out.println(context.getBean("person2MethodCall"));

            System.out.println(context.getBean("person3Parameters"));

            System.out.println(context.getBean("person4Primary"));

            System.out.println(context.getBean("person5Qualifier"));

//        System.out.println(context.getBean(Address.class));

            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
        }
    }
}
