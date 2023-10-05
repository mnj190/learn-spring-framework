package com.mnj190.learnspringframework.section3.section2.game;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.mnj190.learnspringframework.section3.section2.game")
public class GamingAppLauncherApplication {

    public static void main(String args[]) {

        var context = new AnnotationConfigApplicationContext(GamingAppLauncherApplication.class);

        context.getBean(GamingConsole.class).up();
        context.getBean(GameRunner.class).run();

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
