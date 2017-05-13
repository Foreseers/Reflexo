package com.foreseer.reflexo.Statistics;

import com.foreseer.reflexo.Main.MainStatisticsLogger;
import com.foreseer.reflexo.MiniGame;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Foreseer on 11/05/2017.
 * For any questions, feel free to reach me using any of my contacts.
 * Contacts:
 * e-mail (preferred): fforeseer@gmail.com
 */

public class StatisticsPresenterImpl implements StatisticsPresenter {
    private StatisticsView statisticsView;
    private StatisticsReader statisticsReader;

    private Map<MiniGame, String> tabNames;

    public StatisticsPresenterImpl(StatisticsView statisticsView) {
        this.statisticsView = statisticsView;
        statisticsReader = new StatisticsReaderImpl(this);
        tabNames = new HashMap<>();
        initializeTabNames();
    }

    private void initializeTabNames(){
        tabNames.put(MiniGame.TWOSQUARES, "Two Squares");
        tabNames.put(MiniGame.FOURSQUARES, "Four Squares");
    }

    @Override
    public void onStart() {
        statisticsView.initializeTabs();
        for (MiniGame miniGame : MiniGame.values()){
            MainStatisticsLogger.StatisticsBundle bundle = statisticsReader.getStatistics(miniGame, statisticsView.returnActivity());
            int total = Integer.parseInt(bundle.getStatisticsData(MainStatisticsLogger.StatisticsKeys.TOTALTRIES));
            int correct = Integer.parseInt(bundle.getStatisticsData(MainStatisticsLogger.StatisticsKeys.CORRECTTRIES));
            int incorrect = Integer.parseInt(bundle.getStatisticsData(MainStatisticsLogger.StatisticsKeys.INCORRECTTRIES));
            float averageReactionTime = Float.parseFloat(bundle.getStatisticsData(MainStatisticsLogger.StatisticsKeys.AVERAGEREACTIONTIME));
            float percentageCorrect;
            if (total == 0) {
                percentageCorrect = 0;
            } else {
                percentageCorrect = (float) total / (float) correct;
            }
            statisticsView.initializeTab(tabNames.get(miniGame), String.valueOf(total),
                    String.valueOf(correct), String.valueOf(incorrect), String.valueOf(percentageCorrect),
                    String.valueOf(averageReactionTime));
        }
    }
}
