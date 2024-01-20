package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

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
    @Test
    @DisplayName("When rolling twice, score should accumulate and return total")
    void whenRollingTwiceScoreShouldAccumulateAndReturnTotal(){
        game.roll(1);
        game.roll(5);

        assertThat(game.score()).isEqualTo(6);
    }
    @Test
    @DisplayName("After two rolls, currentFrame should increase")
    void afterTwoRollsCurrentFrameShouldIncrease(){
        game.roll(1);
        game.roll(5);

        assertThat(game.getCurrentFrame()).isEqualTo(2);
    }
    @Test
    @DisplayName("Given 11 roll() should throw illegalArgumentException")
    void given11RollShouldThrowIllegalArgumentException(){
        assertThatIllegalArgumentException().isThrownBy(() -> game.roll(11));
    }
    @Test
    @DisplayName("Total of roll() for each frame can not go over 10, should throw exception")
    void totalOfRollForEachFrameCanNotGoOver10ShouldThrowException(){
        game.roll(5);
        assertThatIllegalArgumentException().isThrownBy(() -> game.roll(6));
    }

}