package com.foreseer.reflexo.Statistics;

import android.app.Activity;

/**
 * Created by Foreseer on 11/05/2017.
 * For any questions, feel free to reach me using any of my contacts.
 * Contacts:
 * e-mail (preferred): fforeseer@gmail.com
 */

public interface StatisticsView {
    public void initializeTabs();
    public void initializeTab(String tabName, String totalTries, String correctTries,
                              String incorrectTries, String percentageCorrect,
                              String averageReactionTime);
    public Activity returnActivity();
}
