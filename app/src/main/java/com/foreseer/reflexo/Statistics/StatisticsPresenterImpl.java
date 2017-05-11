package com.foreseer.reflexo.Statistics;

/**
 * Created by Foreseer on 11/05/2017.
 * For any questions, feel free to reach me using any of my contacts.
 * Contacts:
 * e-mail (preferred): fforeseer@gmail.com
 */

public class StatisticsPresenterImpl implements StatisticsPresenter {
    private StatisticsView statisticsView;
    private StatisticsReader statisticsReader;

    public StatisticsPresenterImpl(StatisticsView statisticsView) {
        this.statisticsView = statisticsView;
        statisticsReader = new StatisticsReaderImpl(this);
    }
}
