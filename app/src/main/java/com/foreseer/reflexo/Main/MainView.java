package com.foreseer.reflexo.Main;

import android.app.Activity;

/**
 * Created by Foreseer on 10/05/2017.
 * For any questions, feel free to reach me using any of my contacts.
 * Contacts:
 * e-mail (preferred): fforeseer@gmail.com
 */

public interface MainView {
    public void startSquareActivity();
    public void startTwoSquareActivity();
    public void startStatisticsActivity();
    public void showToast(String message);
    public void showGameChoosingFragment();
    public void removeGameChoosingFragment();
    public void showSeriesChoosingFragment();
    public void removeSeriesChoosingFragment();
    public void hideStartButtons();
    public void showStartButtons();
    public Activity getMainActivity();
}
