package com.foreseer.reflexo.Statistics;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.foreseer.reflexo.Constants;
import com.foreseer.reflexo.Main.MainStatisticsLogger;
import com.foreseer.reflexo.MiniGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Foreseer on 11/05/2017.
 * For any questions, feel free to reach me using any of my contacts.
 * Contacts:
 * e-mail (preferred): fforeseer@gmail.com
 */

public class StatisticsReaderImpl implements StatisticsReader {

    private StatisticsPresenter presenter;

    public StatisticsReaderImpl(StatisticsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public MainStatisticsLogger.StatisticsBundle getStatistics(MiniGame gameType, Activity activity) {
        SharedPreferences statistics = activity.getSharedPreferences(Constants.SHARED_PREFERENCES_FILE_NAME_STATISTICS, Context.MODE_PRIVATE);

        String prekey = gameType.toString() + "_";
        Map<MainStatisticsLogger.StatisticsKeys,String> statisticsList = new HashMap<>();
        for (MainStatisticsLogger.StatisticsKeys key : MainStatisticsLogger.StatisticsKeys.values()) {
            String finalKey = prekey + key.toString();
            statisticsList.put(key, statistics.getString(finalKey, "0"));
        }

        return new MainStatisticsLogger.StatisticsBundle(gameType, statisticsList);
    }
}
