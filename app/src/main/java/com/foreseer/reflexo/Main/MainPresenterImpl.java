package com.foreseer.reflexo.Main;

import com.foreseer.reflexo.Main.MainModel.MainModelListener;

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
        this.mainModel = new MainModelImpl(this);
    }

    @Override
    public void onStartButtonPressed() {
        mainModel.initiateStart();
    }

    @Override
    public void onStartThreeSeriesButtonPressed() {
        count = 3;
        initialCount = 3;
        reactionTimeSum = 0;
        mainModel.initiateStart();
    }

    @Override
    public void gameFinished(long time) {
        if (count != 0) {
            count--;
        }
        if (count == 0 && initialCount == 0){
            mainView.showToast("Took " + ((float) time / 1000) + " seconds!");
        } else {
            if (initialCount != 0 && count == 0){
                float average = reactionTimeSum / initialCount;
                mainView.showToast("Took " + average + " seconds on average of three tries!");
                initialCount = 0;
                reactionTimeSum = 0;
            } else if (initialCount != 0){
                reactionTimeSum += ((float) time / 1000);
                mainView.startSquareActivity();
            }

        }
    }

    @Override
    public void gameFinishedNullReceived() {
        count = 0;
        if (initialCount != 0){
            mainView.showToast("Aborting series!");
        }
        initialCount = 0;
        reactionTimeSum = 0;
    }

    @Override
    public void onGameStart() {
        mainView.startSquareActivity();
    }
}
