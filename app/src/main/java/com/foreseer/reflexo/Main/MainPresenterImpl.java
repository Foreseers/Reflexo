package com.foreseer.reflexo.Main;

import com.foreseer.reflexo.Main.MainModel.MainModelListener;
import com.foreseer.reflexo.MiniGame;

/**
 * Created by Foreseer on 10/05/2017.
 * For any questions, feel free to reach me using any of my contacts.
 * Contacts:
 * e-mail (preferred): fforeseer@gmail.com
 */

public class MainPresenterImpl implements MainPresenter, MainModelListener {

    private MainView mainView;
    private MainModel mainModel;

    private int count;
    private int initialCount;
    private float reactionTimeSum;


    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        mainView.showStartButtons();
        this.mainModel = new MainModelImpl(this);
    }

    @Override
    public void onStartButtonPressed() {
        mainView.hideStartButtons();
        mainView.showGameChoosingFragment();
    }

    @Override
    public void onGameChosen(String gameName) {
        mainView.removeGameChoosingFragment();
        switch (gameName){
            case ("Four Squares"):
                mainModel.setChosenGame(MiniGame.FOURSQUARES);
                break;
            case ("Two Squares"):
                mainModel.setChosenGame(MiniGame.TWOSQUARES);
                break;
        }
        mainView.showSeriesChoosingFragment();
    }

    @Override
    public void onStartGameButtonPressed() {

    }

    @Override
    public void onStartThreeSeriesButtonPressed() {
        //mainModel.initiateStart(3);
    }

    @Override
    public void onSeriesChosen(String seriesChosen) {
        mainView.removeSeriesChoosingFragment();
        switch (seriesChosen.toLowerCase()){
            case ("just one game"):
                mainModel.initiateStart(1);
                break;
            case ("series of three!"):
                mainModel.initiateStart(3);
                break;
        }
    }

    @Override
    public void gameFinished(long time, boolean result) {
        mainView.showStartButtons();
        mainModel.onGameFinished(time, result);
    }

    @Override
    public void gameFinishedNullReceived() {
        mainView.showStartButtons();
        mainModel.onGameAborted();
    }

    @Override
    public void onBackButtonPressed(String stage) {
        if (stage.equals("gameChoose")){
            mainView.removeGameChoosingFragment();
            mainView.showStartButtons();
        } else if (stage.equals("seriesChoose")){
            mainView.removeSeriesChoosingFragment();
            mainView.showGameChoosingFragment();
        } else {
            // Should not ever happen
            mainView.showToast("Back-button error occurred.");
        }
    }

    @Override
    public void onStatisticsButtonPressed() {
        mainView.startStatisticsActivity();
    }

    @Override
    public void onGameStart(MiniGame gameType) {
        if (gameType == MiniGame.FOURSQUARES) {
            mainView.startSquareActivity();
        }

        if (gameType == MiniGame.TWOSQUARES){
            mainView.startTwoSquareActivity();
        }
    }

    @Override
    public void onGameFinished(float time) {
        mainView.showToast("Took " + time + " seconds!");
    }

    @Override
    public void onGameSeriesFinished(float time, int seriesCount) {
        //seriesCount we don't use. Need to map in case of further usage
        mainView.showToast("Took " + time + " seconds on average of three tries!");
    }

    @Override
    public void onGameSeriesAborted() {
        mainView.showToast("Series aborted!");
    }

    @Override
    public void saveStatistics(MiniGame gameType, MainStatisticsLogger.StatisticsBundle bundle) {
        MainStatisticsLogger logger = new MainStatisticsLoggerImpl();
        logger.logStatistics(bundle, mainView.getMainActivity());
    }
}
