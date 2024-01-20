package com.example;

import java.util.*;

public class Game {
    private int score;
    private int currentFrame;
    private int roll;
    public Game(){
        this.score = 0;
        this.currentFrame = 1;
        this.roll = 0;
    }
    public int score(){
        return score;
    }
    public void roll(int pinsKnockedDown){
        if (pinsKnockedDown >10 || pinsKnockedDown < 0)
            throw new IllegalArgumentException("Illegal argument");
        score += pinsKnockedDown;
        roll++;
        if (roll %2 ==0 && roll != 0) {
            currentFrame++;
            roll = 0;
        }
    }

    public int getCurrentFrame() {
        return currentFrame;
    }
}
