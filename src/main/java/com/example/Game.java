package com.example;

import java.util.*;

public class Game {
    private int score;
    private int currentFrame;
    private int roll;
    private int currentPins;
    public Game(){
        this.score = 0;
        this.currentFrame = 1;
        this.roll = 0;
    }
    public int score(){
        return score;
    }
    public void roll(int pinsKnockedDown){
        if (pinsKnockedDown >10 || pinsKnockedDown < 0 || currentPins+pinsKnockedDown > 10)
            throw new IllegalArgumentException("Illegal argument");
        score += pinsKnockedDown;
        currentPins += pinsKnockedDown;
        roll++;
        if (roll % 2 == 0 && roll != 0) {
            currentFrame++;
            roll = 0;
            currentPins = 0;
        }
    }

    public int getCurrentFrame() {
        return currentFrame;
    }
}
