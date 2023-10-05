package com.mnj190.learnspringframework.section2;

import com.mnj190.learnspringframework.section2.game.GamingConsole;
import com.mnj190.learnspringframework.section2.game.GamingConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App03GamingSpringBeans {
    public static void main(String args[]) {

        var context = new AnnotationConfigApplicationContext(GamingConfiguration.class);

        context.getBean(GamingConsole.class).up();

//        var game = new MarioGame();
//        var game = new SuperContraGame();
//        var game = new PacmanGame(); // 객체 생성
//
//        var gameRunner = new GameRunner(game);
//        // 객체 생성 + 의존성 연결
//        // game 는 GameRunner 의 의존성
//
//        gameRunner.run();
    }
}
