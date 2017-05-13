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

    private int correctCount;
    private int incorrectCount;

    private MiniGame gameChosen;

    public MainModelImpl(MainModelListener mainModelListener) {
        this.mainModelListener = mainModelListener;
    }

    @Override
    public void initiateStart(int count) {
        this.count = count;
        this.initialCount = count;
        this.reactionTimeSum = 0;
        this.correctCount = 0;
        this.incorrectCount = 0;

        mainModelListener.onGameStart(gameChosen);
    }

    @Override
    public void onGameFinished(long time, boolean result) {
        if (count != 0) {
            count--;
        }

        if (result){
            correctCount++;
        } else {
            incorrectCount++;
        }
        reactionTimeSum += ((float) time / 1000);

        if (count == 0 && initialCount == 1){
            saveStatistics();
            mainModelListener.onGameFinished(time);
            nullifyFields();
        } else {
            if (initialCount != 0 && count == 0){
                float average = reactionTimeSum / initialCount;
                saveStatistics();
                mainModelListener.onGameSeriesFinished(average, initialCount);
                nullifyFields();
            } else if (initialCount != 0){
                mainModelListener.onGameStart(gameChosen);
            }
        }
    }

    private void saveStatistics(){
        MainStatisticsLogger.StatisticsEntity entities[] = new MainStatisticsLogger.StatisticsEntity[4];
        MainStatisticsLogger.StatisticsEntity entity = new MainStatisticsLogger.StatisticsEntity(MainStatisticsLogger.StatisticsKeys.TOTALTRIES,
                String.valueOf(initialCount));
        entities[0] = entity;

        entity = new MainStatisticsLogger.StatisticsEntity(MainStatisticsLogger.StatisticsKeys.CORRECTTRIES,
                String.valueOf(correctCount));
        entities[1] = entity;

        entity = new MainStatisticsLogger.StatisticsEntity(MainStatisticsLogger.StatisticsKeys.INCORRECTTRIES,
                String.valueOf(incorrectCount));
        entities[2] = entity;

        entity = new MainStatisticsLogger.StatisticsEntity(MainStatisticsLogger.StatisticsKeys.AVERAGEREACTIONTIME,
                String.valueOf(reactionTimeSum / initialCount));
        entities[3] = entity;

        MainStatisticsLogger.StatisticsBundle bundle = new MainStatisticsLogger.StatisticsBundle(gameChosen, entities);
        mainModelListener.saveStatistics(gameChosen, bundle);
    }

    private void nullifyFields(){
        initialCount = 0;
        count = 0;
        gameChosen = null;
        correctCount = 0;
        incorrectCount = 0;
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

    @Override
    public MiniGame getChosenGame() {
        return gameChosen;
    }
}
