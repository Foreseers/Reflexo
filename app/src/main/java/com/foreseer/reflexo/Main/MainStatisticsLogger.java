package com.foreseer.reflexo.Main;

import android.app.Activity;

import com.foreseer.reflexo.MiniGame;

/**
 * Created by Foreseer on 11/05/2017.
 * For any questions, feel free to reach me using any of my contacts.
 * Contacts:
 * e-mail (preferred): fforeseer@gmail.com
 */

public interface MainStatisticsLogger {
    public void logStatistics(StatisticsBundle statisticsBundle, Activity activity);

    public class StatisticsBundle {
        private MiniGame gameType;
        private int totalTries;
        private int correctTries;
        private int incorrectTries;
        private float averageReactionTime;

        public StatisticsBundle(MiniGame gameType, int totalTries, int correctTries, int incorrectTries, float averageReactionTime) {
            this.gameType = gameType;
            this.totalTries = totalTries;
            this.correctTries = correctTries;
            this.incorrectTries = incorrectTries;
            this.averageReactionTime = averageReactionTime;
        }

        public MiniGame getGameType() {
            return gameType;
        }

        public int getTotalTries() {
            return totalTries;
        }

        public int getCorrectTries() {
            return correctTries;
        }

        public int getIncorrectTries() {
            return incorrectTries;
        }

        public float getAverageReactionTime() {
            return averageReactionTime;
        }
    }

    public enum StatisticsKeys {
        TOTALTRIES,
        CORRECTTRIES,
        INCORRECTTRIES,
        AVERAGEREACTIONTIME
    }
}
