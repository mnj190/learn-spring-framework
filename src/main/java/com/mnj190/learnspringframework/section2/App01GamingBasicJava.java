package com.mnj190.learnspringframework.section2;

import com.mnj190.learnspringframework.section2.game.GameRunner;
import com.mnj190.learnspringframework.section2.game.PacmanGame;

public class App01GamingBasicJava {
    public static void main(String args[]) {
//        var game = new MarioGame();
//        var game = new SuperContraGame();
        var game = new PacmanGame(); // 객체 생성

        var gameRunner = new GameRunner(game);
        // 객체 생성 + 의존성 연결
        // game 는 GameRunner 의 의존성

        gameRunner.run();
    }
}
