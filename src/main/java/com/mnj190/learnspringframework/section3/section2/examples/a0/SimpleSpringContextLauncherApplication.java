 package com.mnj190.learnspringframework.section3.section2.examples.a0;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

 @Configuration
@ComponentScan("com.mnj190.learnspringframework.section3.section2.examples.a1")
public class SimpleSpringContextLauncherApplication {

    public static void main(String args[]) {

        var context = new AnnotationConfigApplicationContext(SimpleSpringContextLauncherApplication.class);

        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
    }
}
