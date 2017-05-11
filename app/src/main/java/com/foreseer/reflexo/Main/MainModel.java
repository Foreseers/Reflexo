package com.foreseer.reflexo.Main;

import com.foreseer.reflexo.MiniGame;

/**
 * Created by Foreseer on 10/05/2017.
 * For any questions, feel free to reach me using any of my contacts.
 * Contacts:
 * e-mail (preferred): fforeseer@gmail.com
 */

public interface MainModel {
    public void initiateStart(int count);
    public void onGameFinished(long time, boolean result);
    public void onGameAborted();
    public void setChosenGame(MiniGame gameType);
    public MiniGame getChosenGame();

    public interface MainModelListener {
        public void onGameStart(MiniGame gameType);
        public void onGameFinished(long time);
        public void onGameSeriesFinished(float time, int seriesCount);
        public void onGameSeriesAborted();
        public void saveStatistics(MiniGame gameType, int initialCount, int correctTries, int incorrectTries,
                                   float averageReactionTime);
    }
}
