package com.mnj190.learnspringframework.section3.section2.examples.a2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Arrays;


interface DataService {
    int[] retrieveData();
}

@Primary
@Component
class MongoDbDataService implements DataService {
    @Override
    public int[] retrieveData() {
        return new int[] { 11, 22, 33, 44, 55 };
    }
}

@Component
class MySQLDataService implements DataService {
    @Override
    public int[] retrieveData() {
        return new int[] { 1, 2, 3, 4, 5 };
    }
}

@Configuration
@ComponentScan
public class BusinessCalculationService {
    DataService dataService;

    public BusinessCalculationService(DataService dataService) {
        System.out.println(dataService);
        this.dataService = dataService;
    }

    public int findMax() {
        return Arrays.stream(dataService.retrieveData()).max().orElse(0);
    }

    public static void main(String args[]) {
        var context = new AnnotationConfigApplicationContext(BusinessCalculationService.class);

        System.out.println(context.getBean(BusinessCalculationService.class).findMax());

//        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
    }
}

