package com.example;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int currentFrame;
    private int roll;
    private int currentPinsKnockedDown;
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
            if (frame10SpareOrStrike(i)) return score;
            if (strikeOrSpare(i)) score = getPointsForStrikeAndSpare(i, score);
            else
                score += scoreFromNormalRollInAFrame(i);
        }
        return score;
    }

    private int scoreFromNormalRollInAFrame(int i) {
        return Integer.parseInt(frames.get(i).substring(0, 1)) + Integer.parseInt(frames.get(i).substring(1, 2));
    }

    private boolean strikeOrSpare(int i) {
        return checkIfStrike(i) || checkIfSpare(i);
    }

    private boolean checkIfSpare(int i) {
        return frames.get(i).contains("/");
    }

    private boolean checkIfStrike(int i) {
        return frames.get(i).equals("X");
    }

    private boolean frame10SpareOrStrike(int i) {
        return (i == 10 && checkIfStrike(9)) || (i == 10 && checkIfSpare(9));
    }

    private int getPointsForStrikeAndSpare(int i, int score) {
        if (checkIfStrikeOrSpareIsLastInFrames(i)) {
            if (checkIfStrike(i)) score = strikeBonusPoints(i, score);
            else score = spareBonusPoints(i, score);
        }
        return score;
    }

    private boolean checkIfStrikeOrSpareIsLastInFrames(int i) {
        return i != frames.size() - 1;
    }

    private int spareBonusPoints(int i, int score) {
        if (checkIfStrike(i + 1)) score += 20;
        else {
            score += 10 + Integer.parseInt(frames.get(i + 1).substring(0, 1));
        }
        return score;
    }

    private int strikeBonusPoints(int i, int score) {
        if (checkIfStrike(i + 1)) {
            if (checkIfStrikeOrSpareIsLastInFrames(i + 1)) {
                if (checkIfStrike(i + 2)) {
                    score += 30;
                } else {
                    score += 20 + Integer.parseInt(frames.get(i + 2).substring(0, 1));
                }
            }
        } else if (checkIfSpare(i + 1)) {
            score += 20;
        } else {
            score += 10 + Integer.parseInt(frames.get(i + 1).substring(0, 1)) + Integer.parseInt(frames.get(i + 1).substring(1, 2));
        }
        return score;
    }

    public void roll(int pinsKnockedDown) {
        roll++;
        checkIfValidThrow(pinsKnockedDown);
        checkIfGameOver();
        if (currentFrame >= 10) {
            frameTen(pinsKnockedDown);
        } else {
            frameOneToNine(pinsKnockedDown);
        }
        savePinsForNextRoll(pinsKnockedDown);
    }

    private void savePinsForNextRoll(int pinsKnockedDown) {
        if (roll == 1) currentPinsKnockedDown += pinsKnockedDown;
    }

    private void frameOneToNine(int pinsKnockedDown) {
        if (allPinsKnockedDown(pinsKnockedDown) && roll == 2) {
            addToFrame(currentPinsKnockedDown + "/");
        } else if (allPinsKnockedDown(pinsKnockedDown) && roll == 1) {
            addToFrame("X");
        } else if (neitherStrikeOrSpare()) {
            addToFrame(currentPinsKnockedDown + "" + pinsKnockedDown);
        }
    }

    private boolean allPinsKnockedDown(int pinsKnockedDown) {
        return currentPinsKnockedDown + pinsKnockedDown == 10;
    }

    private void checkIfGameOver() {
        if (gameOver) {
            throw new IndexOutOfBoundsException("The game is over");
        }
    }

    private void frameTen(int pinsKnockedDown) {
        if (currentFrame == 13) {
            gameOver = true;
            return;
        }
        if (pinsKnockedDown == 10) {
            addToFrame("X");
        } else if (allPinsKnockedDown(pinsKnockedDown)) {
            addToFrame(currentPinsKnockedDown + "/");
        } else if (neitherStrikeOrSpare()) {
            addToFrame(currentPinsKnockedDown + "" + pinsKnockedDown);
            gameOver = true;
        } else if (frame10SpareOrFrame10DoubleStrike()) {
            addToFrame(pinsKnockedDown + "");
            gameOver = true;
        }
    }

    private boolean neitherStrikeOrSpare() {
        return roll == 2;
    }

    private boolean frame10SpareOrFrame10DoubleStrike() {
        return roll == 1 && checkIfSpare(currentFrame - 2) || currentFrame == 12;
    }

    private void checkIfValidThrow(int pinsKnockedDown) {
        if (pinsKnockedDown > 10 || pinsKnockedDown < 0 || currentPinsKnockedDown + pinsKnockedDown > 10) {
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
        currentPinsKnockedDown = 0;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }
}
