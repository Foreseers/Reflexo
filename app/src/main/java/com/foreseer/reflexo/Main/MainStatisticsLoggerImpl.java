package com.foreseer.reflexo.Main;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.foreseer.reflexo.Constants;

import java.util.Map;

/**
 * Created by Foreseer on 11/05/2017.
 * For any questions, feel free to reach me using any of my contacts.
 * Contacts:
 * e-mail (preferred): fforeseer@gmail.com
 */

public class MainStatisticsLoggerImpl implements MainStatisticsLogger{
    @Override
    public void logStatistics(StatisticsBundle statisticsBundle, Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(Constants.SHARED_PREFERENCES_FILE_NAME_STATISTICS,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String gameType = statisticsBundle.getGameType().toString();
        String preKey = gameType + "_";

        for (Map.Entry<StatisticsKeys, String> entry : statisticsBundle.getEntrySet()){
            String finalKey = preKey + entry.getKey().toString();
            String value = entry.getValue();

            String oldValue = sharedPreferences.getString(finalKey, "0");
            int oldValueInt;
            float oldValueFloat;
            if (entry.getKey() != StatisticsKeys.AVERAGEREACTIONTIME){
                int finalValue = Integer.parseInt(oldValue) + Integer.parseInt(value);
                editor.putString(finalKey, String.valueOf(finalValue));
            } else {
                int oldAmount = Integer.parseInt(sharedPreferences.getString(preKey + StatisticsKeys.TOTALTRIES, "0"));
                int totalAmount = oldAmount + Integer.parseInt(statisticsBundle.getStatisticsData(StatisticsKeys.TOTALTRIES));

                float oldReactionTime = Float.parseFloat(sharedPreferences.getString(preKey + StatisticsKeys.AVERAGEREACTIONTIME, "0"));
                oldReactionTime = oldReactionTime * oldAmount;

                float newReactionTime = Float.parseFloat(statisticsBundle.getStatisticsData(StatisticsKeys.AVERAGEREACTIONTIME));
                newReactionTime = newReactionTime * Float.parseFloat(statisticsBundle.getStatisticsData(StatisticsKeys.TOTALTRIES));

                float totalReactionTime = oldReactionTime + newReactionTime;

                editor.putString(finalKey, String.valueOf(totalReactionTime / totalAmount));
            }
        }
        editor.apply();
    }
}
