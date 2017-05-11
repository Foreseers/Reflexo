package com.foreseer.reflexo.Main;

/**
 * Created by Foreseer on 10/05/2017.
 * For any questions, feel free to reach me using any of my contacts.
 * Contacts:
 * e-mail (preferred): fforeseer@gmail.com
 */

public interface MainView {
    public void startSquareActivity();
    public void startTwoSquareActivity();
    public void showToast(String message);
    public void showGameChoosingFragment();
    public void removeGameChoosingFragment();
    public void showSeriesChoosingFragment();
    public void removeSeriesChoosingFragment();
    public void hideStartButton();
    public void showStartButton();
}
