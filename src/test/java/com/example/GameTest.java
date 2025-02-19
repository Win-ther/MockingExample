package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

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
    @DisplayName("When given roll value of 1 and not a second roll, score should return 0")
    void whenGivenRollValueOf1AndNotASecondRollScoreShouldReturn0(){
        game.roll(1);

        assertThat(game.score()).isZero();
    }
    @Test
    @DisplayName("When rolling twice, score should accumulate and return total")
    void whenRollingTwiceScoreShouldAccumulateAndReturnTotal(){
        game.roll(1);
        game.roll(5);

        assertThat(game.score()).isEqualTo(6);
    }
    @Test
    @DisplayName("Given two rolls and currentPins are below 10, currentFrame should increase")
    void givenTwoRollsCurrentFrameShouldIncrease(){
        game.roll(1);
        game.roll(5);

        assertThat(game.getCurrentFrame()).isEqualTo(2);
    }
    @Test
    @DisplayName("Given two rolls and currentPins are 10, currentFrame should increase")
    void givenTwoRollsAndCurrentPinsAre10CurrentFrameShouldIncrease(){
        game.roll(5);
        game.roll(5);

        assertThat(game.getCurrentFrame()).isEqualTo(2);
    }
    @Test
    @DisplayName("Given one roll and currentPins are 10, currentFrame should increase")
    void givenOneRollAndCurrentPinsAre10CurrentFrameShouldIncrease(){
        game.roll(10);

        assertThat(game.getCurrentFrame()).isEqualTo(2);
    }
    @Test
    @DisplayName("Given 11 roll() should throw illegalArgumentException")
    void given11RollShouldThrowIllegalArgumentException(){
        assertThatIllegalArgumentException().isThrownBy(() -> game.roll(11));
    }
    @Test
    @DisplayName("Given two rolls and pin total is over 10, should throw exception")
    void givenTwoRollsAndPinTotalIsOver10ShouldThrowException(){
        game.roll(5);
        assertThatIllegalArgumentException().isThrownBy(() -> game.roll(6));
    }
    @Test
    @DisplayName("Given spare, score should increase by next frames first roll")
    void givenSpareScoreShouldIncreaseByNextFramesFirstRoll(){
        game.roll(7);
        game.roll(3);
        game.roll(5);
        game.roll(3);

        assertThat(game.score()).isEqualTo(23);
    }
    @Test
    @DisplayName("Given strike, score should increase by next two rolls")
    void givenStrikeScoreShouldIncreaseByNextTwoRolls(){
        game.roll(10);
        game.roll(5);
        game.roll(3);

        assertThat(game.score()).isEqualTo(26);
    }
    @Test
    @DisplayName("If latest in frames is spare, it should not have any points yet")
    void ifLatestInFramesIsSpareItShouldNotHaveAnyPointsYet(){
        game.roll(5);
        game.roll(3);
        game.roll(3);
        game.roll(7);

        assertThat(game.score()).isEqualTo(8);
    }
    @Test
    @DisplayName("If latest in frames is strike it should not have any points yet")
    void ifLatestInFramesIsStrikeItShouldNotHaveAnyPointsYet(){
        game.roll(5);
        game.roll(3);
        game.roll(10);

        assertThat(game.score()).isEqualTo(8);
    }
    @Test
    @DisplayName("Given strike then spare, should return 20 for strike and nothing for spare")
    void givenStrikeThenSpareShouldReturn20ForStrikeAndNothingForSpare(){
        game.roll(10);
        game.roll(7);
        game.roll(3);

        assertThat(game.score()).isEqualTo(20);
    }
    @Test
    @DisplayName("If given strike then strike, should return 0")
    void ifGivenStrikeThenStrikeShouldReturn0(){
        game.roll(10);
        game.roll(10);

        assertThat(game.score()).isZero();
    }
    @Test
    @DisplayName("If given three strikes in a row, should return 30")
    void ifGivenThreeStrikesInARowShouldReturn30(){
        game.roll(10);
        game.roll(10);
        game.roll(10);

        assertThat(game.score()).isEqualTo(30);
    }
    @Test
    @DisplayName("If currentframe is 10 and given rolls are not strike or spare, the game is over and will throw IndexOutOfBoundsException")
    void ifCurrentframeIs10AndGivenRollsAreNotStrikeOrSpareTheGameIsOverAndWillThrowIndexOutOfBoundsException(){
        rollNineFrames();
        game.roll(3);
        game.roll(3);

        assertThatIndexOutOfBoundsException().isThrownBy(() -> game.roll(6));
    }

    @Test
    @DisplayName("If current frame is 10 and strike, should be able to roll twice more")
    void ifCurrentFrameIs10AndStrikeShouldBeAbleToRollTwiceMore(){
        rollNineFrames();
        game.roll(10);

        assertThatNoException().isThrownBy(() -> game.roll(1));
        assertThatNoException().isThrownBy(() -> game.roll(1));
        assertThat(game.score()).isEqualTo(130);
    }
    @Test
    @DisplayName("If strike is given to frame 10, and two more strikes are rolled should return 148 score")
    void ifStrikeIsGivenToFrame10AndTwoMoreStrikesAreRolledShouldReturn148Score(){
        rollNineFrames();
        game.roll(10);

        assertThatNoException().isThrownBy(() -> game.roll(10));
        assertThatNoException().isThrownBy(() -> game.roll(10));
        assertThat(game.score()).isEqualTo(148);
    }
    @Test
    @DisplayName("given strike strike 1 to frame 10, should return 139 score")
    void ifStrikeIsGivenToFrame10AndOneStrikeAndANormalRollIsGivenShouldReturn139Score(){
        rollNineFrames();
        game.roll(10);

        assertThatNoException().isThrownBy(() -> game.roll(10));
        assertThatNoException().isThrownBy(() -> game.roll(1));
        assertThat(game.score()).isEqualTo(139);
    }
    @Test
    @DisplayName("If current frame is 10 and spare, should be able to roll once more")
    void ifCurrentFrameIs10AndSpareShouldBeAbleToRollOnceMore(){
        rollNineFrames();
        game.roll(9);
        game.roll(1);

        assertThatNoException().isThrownBy(() -> game.roll(5));
        assertThat(game.score()).isEqualTo(133);
    }

    @Test
    @DisplayName("If all frames are given Strike, should  return 300 score")
    void ifAllFramesAreGivenStrikeShouldReturn300Score() {
        for (int i = 0; i < 13; i++) {
            game.roll(10);
        }

        assertThat(game.score()).isEqualTo(300);
    }

    @Test
    @DisplayName("If all frames are given 0, should return 0")
    void ifAllFramesAreGiven0ShouldReturn0() {
        for (int i = 0; i < 10; i++) {
            game.roll(0);
            game.roll(0);
        }

        assertThat(game.score()).isZero();
    }

    private void rollNineFrames() {
        game.roll(5);
        game.roll(5);
        game.roll(10);
        game.roll(8);
        game.roll(1);
        game.roll(1);
        game.roll(0);
        game.roll(10);
        game.roll(5);
        game.roll(5);
        game.roll(10);
        game.roll(8);
        game.roll(1);
        game.roll(1);
        game.roll(0);
    }
}