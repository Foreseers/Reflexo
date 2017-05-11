package com.foreseer.reflexo.Statistics;

import com.foreseer.reflexo.Main.MainStatisticsLogger;
import com.foreseer.reflexo.MiniGame;

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
    public MainStatisticsLogger.StatisticsBundle getStatistics(MiniGame gameType) {
        return null;
    }
}
