package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game;
    @BeforeEach
    void setUp(){
        game = new Game();
    }
    @Test
    @DisplayName("When game is started, score should return 0")
    void whenGameIsStartedScoreShouldReturn0(){
        assertThat(game.score()).isZero();
    }
    @Test
    @DisplayName("When given roll value of 1, score should return 1")
    void whenGivenRollValueOf1ScoreShouldReturn1(){
        game.roll(1);

        assertThat(game.score()).isOne();
    }

}