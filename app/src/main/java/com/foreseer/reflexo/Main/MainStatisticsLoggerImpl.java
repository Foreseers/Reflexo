package com.foreseer.reflexo.Main;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Foreseer on 11/05/2017.
 * For any questions, feel free to reach me using any of my contacts.
 * Contacts:
 * e-mail (preferred): fforeseer@gmail.com
 */

public class MainStatisticsLoggerImpl implements MainStatisticsLogger{
    @Override
    public void logStatistics(StatisticsBundle statisticsBundle, Activity activity) {
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String gameType = statisticsBundle.getGameType().toString();
        String preKey = gameType + "_";

        String finalKey = preKey + StatisticsKeys.TOTALTRIES.toString();
        editor.putString(finalKey, String.valueOf(statisticsBundle.getTotalTries()));

        finalKey = preKey + StatisticsKeys.CORRECTTRIES.toString();
        editor.putString(finalKey, String.valueOf(statisticsBundle.getCorrectTries()));

        finalKey = preKey + StatisticsKeys.INCORRECTTRIES.toString();
        editor.putString(finalKey, String.valueOf(statisticsBundle.getIncorrectTries()));

        finalKey = preKey + StatisticsKeys.AVERAGEREACTIONTIME.toString();
        editor.putString(finalKey, String.valueOf(statisticsBundle.getAverageReactionTime()));

        editor.apply();
    }
}
