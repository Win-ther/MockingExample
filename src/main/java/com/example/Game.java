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
            //Check if strike or spare was in 10th frame, then return if true
            if ((i == 10 && frames.get(9).equals("X")) || (i == 10 && frames.get(9).contains("/")))
                return score;
            if (frames.get(i).equals("X") || frames.get(i).contains("/")) {
                score = getPointsForStrikeAndSpare(i, score);
            } else
                score += Integer.parseInt(frames.get(i).substring(0, 1)) + Integer.parseInt(frames.get(i).substring(1, 2));
        }
        return score;
    }

    private int getPointsForStrikeAndSpare(int i, int score) {
        //Check if Strike/Spare is in latest frame, if it is, it does not have points yet.
        if (i != frames.size() - 1) {
            if (frames.get(i).equals("X"))
                score = strikeBonusPoints(i, score);
            else
                score = spareBonusPoints(i, score);
        }
        return score;
    }

    private int spareBonusPoints(int i, int score) {
        if (frames.get(i + 1).equals("X"))
            score += 20;
        else {
            score += 10 + Integer.parseInt(frames.get(i + 1).substring(0, 1));
        }
        return score;
    }

    private int strikeBonusPoints(int i, int score) {
        if (frames.get(i + 1).equals("X")) {
            //Checks if Strike is in second to last frame, if it is, current Strike does not have points yet
            if (i + 1 != frames.size() - 1) {
                if (frames.get(i + 2).equals("X")) {
                    score += 30;
                } else {
                    score += 20 + Integer.parseInt(frames.get(i + 2).substring(0, 1));
                }
            }
        } else if (frames.get(i + 1).contains("/")) {
            score += 20;
        } else {
            score += 10 + Integer.parseInt(frames.get(i + 1).substring(0, 1))
                    + Integer.parseInt(frames.get(i + 1).substring(1, 2));
        }
        return score;
    }

    public void roll(int pinsKnockedDown) {
        roll++;
        checkIfValidThrow(pinsKnockedDown);
        checkIfGameOver();
        if (currentFrame >= 10) {
            frameTen(pinsKnockedDown);
        } else{
            frameOneToNine(pinsKnockedDown);
        }
        if (roll == 1)
            currentPins += pinsKnockedDown;
    }

    private void frameOneToNine(int pinsKnockedDown) {
        if (currentPins + pinsKnockedDown == 10 && roll == 2) {
            addToFrame((10 - pinsKnockedDown) + "/");
        } else if (currentPins + pinsKnockedDown == 10 && roll == 1) {
            addToFrame("X");
        } else if (roll == 2) {
            addToFrame(currentPins + "" + pinsKnockedDown);
        }
    }

    private void checkIfGameOver() {
        if (gameOver) {
            throw new IndexOutOfBoundsException("The game is over");
        }
    }

    private void frameTen(int pinsKnockedDown) {
        if (pinsKnockedDown == 10) {
            addToFrame("X");
        } else if (currentPins + pinsKnockedDown == 10) {
            addToFrame(currentPins + "/");
        } else if (roll == 2) {
            addToFrame(currentPins + "" + pinsKnockedDown);
            gameOver = true;
        } else if (roll == 1 && frames.get(currentFrame - 2).contains("/") || currentFrame == 12) {
            addToFrame(pinsKnockedDown + "");
            gameOver = true;
        }
    }

    private void checkIfValidThrow(int pinsKnockedDown) {
        if (pinsKnockedDown > 10 || pinsKnockedDown < 0 || currentPins + pinsKnockedDown > 10) {
            throw new IllegalArgumentException("Illegal argument");
        }
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
