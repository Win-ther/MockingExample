package com.example;

import java.util.HashMap;
import java.util.Map;

public class Game {
    private int score;
    public int score(){
        return score;
    }
    public void roll(int pinsKnockedDown){
        score += pinsKnockedDown;
    }
}
