package com.example;

import java.util.*;

public class Game {
    private int currentFrame;
    private int roll;
    private int currentPins;
    private List<String> frames;

    public Game() {
        this.frames = new ArrayList<>();
        this.currentFrame = 1;
        this.roll = 0;
    }

    public int score() {
        int score = 0;
        for (int i = 0; i < frames.size(); i++) {
            //ToDo: Om senaste rutan är Strike eller Spare, returnera score då Spare är 10+nästa roll och Strike är 10+2 nästa rolls

            if (frames.get(i).equals("X") && i != frames.size()-1) {
                if (frames.get(i+1).equals("X")){
                    if (frames.get(i+2).equals("X")) {
                        score += 30;
                    }else {
                        score += 20 + Integer.parseInt(frames.get(i+2).substring(0,1));
                    }
                } else if (frames.get(i+1).contains("/")) {
                    score += 20;
                }else{
                    score += 10     + Integer.parseInt(frames.get(i+1).substring(0,1))
                                    +Integer.parseInt(frames.get(i+1).substring(1,2));
                }
            }else if (frames.get(i).contains("/") && i != frames.size()-1) {
                if (frames.get(i+1).equals("X"))
                    score += 20;
                else{
                    score += 10 + Integer.parseInt(frames.get(i+1).substring(0,1));
                }
            }else {
                if (frames.get(i).contains("/")||frames.get(i).equals("X"))
                    score += 0;
                else score += Integer.parseInt(frames.get(i).substring(0,1))
                        +Integer.parseInt(frames.get(i).substring(1,2));
            }
        }
        return score;
    }

    public void roll(int pinsKnockedDown) {
        roll++;
        if (pinsKnockedDown > 10 || pinsKnockedDown < 0 || currentPins+pinsKnockedDown > 10) {
            throw new IllegalArgumentException("Illegal argument");
        }
        if (currentPins+pinsKnockedDown == 10 && roll == 2) {
            this.frames.add(currentFrame - 1, (10-pinsKnockedDown)+"/");
            increaseFrameAndResetRollAndCurrentPins();
        } else if (currentPins+pinsKnockedDown == 10 && roll == 1) {
            this.frames.add(currentFrame - 1, "X");
            increaseFrameAndResetRollAndCurrentPins();
        } else if (roll == 2) {
            this.frames.add(currentFrame - 1, currentPins+""+pinsKnockedDown);
            increaseFrameAndResetRollAndCurrentPins();
        }
        if (roll == 1)
            currentPins += pinsKnockedDown;
    }

    private void increaseFrameAndResetRollAndCurrentPins() {
        currentFrame++;
        roll = 0;
        currentPins = 0;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }
}
