package com.foreseer.reflexo.Main;

import android.app.Activity;

import com.foreseer.reflexo.MiniGame;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Foreseer on 11/05/2017.
 * For any questions, feel free to reach me using any of my contacts.
 * Contacts:
 * e-mail (preferred): fforeseer@gmail.com
 */

public interface MainStatisticsLogger {
    public void logStatistics(StatisticsBundle statisticsBundle, Activity activity);

    public class StatisticsBundle {
        private MiniGame gameType;
        private Map<StatisticsKeys, String> statisticsData;

        public StatisticsBundle(MiniGame gameType, StatisticsEntity entities[]) {
            this.gameType = gameType;
            statisticsData = new HashMap<>();

            for (StatisticsEntity entity : entities){
                statisticsData.put(entity.getKey(), entity.getValue());
            }
        }

        public StatisticsBundle(MiniGame gameType, Map<StatisticsKeys, String> entities){
            this.gameType = gameType;
            statisticsData = entities;
        }

        public Set<Map.Entry<StatisticsKeys, String>> getEntrySet(){
            return statisticsData.entrySet();
        }

        public MiniGame getGameType() {
            return gameType;
        }

        public String getStatisticsData(StatisticsKeys key){
            if (statisticsData.containsKey(key)){
                return statisticsData.get(key);
            } else {
                return null;
            }
        }
    }

    public enum StatisticsKeys {
        TOTALTRIES,
        CORRECTTRIES,
        INCORRECTTRIES,
        AVERAGEREACTIONTIME
    }

    public class StatisticsEntity {
        private StatisticsKeys key;
        private String value;

        public StatisticsEntity(StatisticsKeys key, String value) {
            this.key = key;
            this.value = value;
        }

        public StatisticsKeys getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }
}
