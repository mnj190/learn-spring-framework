package com.mnj190.learnspringframework.section2.game;

public class GameRunner {
//    MarioGame game;
    private GamingConsole game;

    public GameRunner(GamingConsole game) {
        this.game = game;
    }

    public void run() {
        System.out.println("Running game : " + game);

        game.up();
        game.down();
        game.left();
        game.right();
    }
}
