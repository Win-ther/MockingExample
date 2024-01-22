package com.example;

import java.util.*;

public class Game {
    private int currentFrame;
    private int roll;
    private int currentPins;
    private boolean gameOver;
    private List<String> frames;

    public Game() {
        this.frames = new ArrayList<>();
        this.currentFrame = 1;
        this.roll = 0;
        gameOver = false;
    }

    public int score() {
        int score = 0;
        for (int i = 0; i < frames.size(); i++) {
            if (i == 10)
                return score;
            if (frames.get(i).equals("X") && i != frames.size() - 1) {
                if (frames.get(i + 1).equals("X")) {
                    if (i + 1 == frames.size() - 1) {
                        score += 0;
                    } else if (frames.get(i + 2).equals("X")) {
                        score += 30;
                    } else {
                        score += 20 + Integer.parseInt(frames.get(i + 2).substring(0, 1));
                    }
                } else if (frames.get(i + 1).contains("/")) {
                    score += 20;
                } else {
                    score += 10 + Integer.parseInt(frames.get(i + 1).substring(0, 1))
                            + Integer.parseInt(frames.get(i + 1).substring(1, 2));
                }
            } else if (frames.get(i).contains("/") && i != frames.size() - 1) {
                if (frames.get(i + 1).equals("X"))
                    score += 20;
                else {
                    score += 10 + Integer.parseInt(frames.get(i + 1).substring(0, 1));
                }
            } else {
                if (frames.get(i).contains("/") || frames.get(i).equals("X"))
                    score += 0;
                else score += Integer.parseInt(frames.get(i).substring(0, 1))
                        + Integer.parseInt(frames.get(i).substring(1, 2));
            }
        }
        return score;
    }

    public void roll(int pinsKnockedDown) {
        roll++;
        if (pinsKnockedDown > 10 || pinsKnockedDown < 0 || currentPins + pinsKnockedDown > 10) {
            throw new IllegalArgumentException("Illegal argument");
        }
        if (currentFrame >= 10 && !gameOver) {
            if (pinsKnockedDown == 10){
                addToFrame("X");
            } else if (currentPins+pinsKnockedDown == 10) {
                addToFrame((10 - pinsKnockedDown) + "/");
            } else if (roll == 2){
                addToFrame(currentPins + "" + pinsKnockedDown);
                gameOver = true;
            }
        } else if (currentFrame > 10) {
            throw new IndexOutOfBoundsException("The game is over");
        } else if (currentPins + pinsKnockedDown == 10 && roll == 2) {
            addToFrame((10 - pinsKnockedDown) + "/");
        } else if (currentPins + pinsKnockedDown == 10 && roll == 1) {
            addToFrame("X");
        } else if (roll == 2) {
            addToFrame(currentPins + "" + pinsKnockedDown);
        }
        if (roll == 1)
            currentPins += pinsKnockedDown;
    }

    private void addToFrame(String result) {
        this.frames.add(currentFrame - 1, result);
        increaseFrameAndResetRollAndCurrentPins();
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
