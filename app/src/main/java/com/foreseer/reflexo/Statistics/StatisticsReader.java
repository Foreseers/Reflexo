package com.foreseer.reflexo.Statistics;

import android.app.Activity;

import com.foreseer.reflexo.Main.MainStatisticsLogger;
import com.foreseer.reflexo.MiniGame;

/**
 * Created by Foreseer on 11/05/2017.
 * For any questions, feel free to reach me using any of my contacts.
 * Contacts:
 * e-mail (preferred): fforeseer@gmail.com
 */

public interface StatisticsReader {
    public MainStatisticsLogger.StatisticsBundle getStatistics(MiniGame gameType, Activity activity);
}
