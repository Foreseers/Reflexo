package com.foreseer.reflexo.Main;

import com.foreseer.reflexo.MiniGame;

/**
 * Created by Foreseer on 10/05/2017.
 * For any questions, feel free to reach me using any of my contacts.
 * Contacts:
 * e-mail (preferred): fforeseer@gmail.com
 */

public class MainModelImpl implements MainModel {
    private MainModelListener mainModelListener;

    private int count;
    private int initialCount;
    private float reactionTimeSum;

    private MiniGame gameChosen;

    public MainModelImpl(MainModelListener mainModelListener) {
        this.mainModelListener = mainModelListener;
    }

    @Override
    public void initiateStart(int count) {
        this.count = count;
        this.initialCount = count;
        this.reactionTimeSum = 0;

        mainModelListener.onGameStart(gameChosen);
    }

    @Override
    public void onGameFinished(long time) {
        if (count != 0) {
            count--;
        }
        if (count == 0 && initialCount == 0){
            gameChosen = null;
            mainModelListener.onGameFinished(time);
        } else {
            if (initialCount != 0 && count == 0){
                float average = reactionTimeSum / initialCount;
                mainModelListener.onGameSeriesFinished(time, initialCount);
                initialCount = 0;
                reactionTimeSum = 0;
                gameChosen = null;
            } else if (initialCount != 0){
                reactionTimeSum += ((float) time / 1000);
                mainModelListener.onGameStart(gameChosen);
            }
        }
    }

    @Override
    public void onGameAborted() {
        count = 0;
        if (initialCount != 0){
            mainModelListener.onGameSeriesAborted();
        }
        initialCount = 0;
        reactionTimeSum = 0;
    }

    @Override
    public void setChosenGame(MiniGame gameType) {
        this.gameChosen = gameType;
    }
}
