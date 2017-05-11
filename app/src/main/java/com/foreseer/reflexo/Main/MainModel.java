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
    public void onGameFinished(long time);
    public void onGameAborted();
    public void setChosenGame(MiniGame gameType);

    public interface MainModelListener {
        public void onGameStart(MiniGame gameType);
        public void onGameFinished(long time);
        public void onGameSeriesFinished(long time, int seriesCount);
        public void onGameSeriesAborted();
    }
}
