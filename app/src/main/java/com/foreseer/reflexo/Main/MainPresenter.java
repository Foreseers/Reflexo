package com.foreseer.reflexo.Main;

/**
 * Created by Foreseer on 10/05/2017.
 * For any questions, feel free to reach me using any of my contacts.
 * Contacts:
 * e-mail (preferred): fforeseer@gmail.com
 */

public interface MainPresenter {
    public void onStartButtonPressed();
    public void onStartThreeSeriesButtonPressed();
    public void gameFinished(long time);
    public void gameFinishedNullReceived();
}
