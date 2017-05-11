package com.foreseer.reflexo.Main;

import com.foreseer.reflexo.MiniGame;

/**
 * Created by Foreseer on 10/05/2017.
 * For any questions, feel free to reach me using any of my contacts.
 * Contacts:
 * e-mail (preferred): fforeseer@gmail.com
 */

public interface MainPresenter {
    public void onStartButtonPressed();
    public void onGameChosen(String gameName);
    public void onStartGameButtonPressed();
    public void onStartThreeSeriesButtonPressed();
    public void onSeriesChosen(String seriesChosen);
    public void gameFinished(long time);
    public void gameFinishedNullReceived();
    public void onBackButtonPressed(String stage);
}
