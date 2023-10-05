package com.mnj190.learnspringframework.section2.game;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

public class MarioGame implements com.mnj190.learnspringframework.section2.game.GamingConsole {
    public void up() {
        System.out.println("Jump");
    }

    public void down() {
        System.out.println("Go into a hole");
    }

    public void left() {
        System.out.println("Go back");
    }

    public void right() {
        System.out.println("Accelerate");
    }
}
