package com.foreseer.reflexo.TwoSquareGame;

import com.foreseer.reflexo.IntUtils;
import com.foreseer.reflexo.MiniGames.SquareMiniGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Foreseer on 10/05/2017.
 * For any questions, feel free to reach me using any of my contacts.
 * Contacts:
 * e-mail (preferred): fforeseer@gmail.com
 */

public class TwoSquareGameModelImpl implements TwoSquareGameModel {
    private SquareGameModelListener listener;

    private Date startDate;

    private SquareMiniGame.SquareGameData gameData;

    private Map<String, String> colorHexNames;

    private boolean result;

    public TwoSquareGameModelImpl(SquareGameModelListener listener) {
        this.listener = listener;
        fillColourMap();
        initializeGame();
    }

    public void initializeGame() {
        listener.onGameDataReceived(getNewGameData());
    }

    private void fillColourMap(){
        colorHexNames = new HashMap<>();

        colorHexNames.put("#FFFF00", "YELLOW");
        colorHexNames.put("#00FF00", "GREEN");
        colorHexNames.put("#FF0000", "RED");
        colorHexNames.put("#0000FF", "BLUE");
    }

    @Override
    public void onColorChosen(String color) {
        long time = new Date().getTime() - startDate.getTime();
        result = color.equals(gameData.getWinningCode());
        listener.onGameFinished(result, time);
    }

    @Override
    public void onDestroy() {
        listener = null;
    }

    @Override
    public void onStart() {
        startDate = new Date();
    }

    @Override
    public String getColorName(String hexCode) {
        return colorHexNames.get(hexCode);
    }

    @Override
    public boolean getResult() {
        return result;
    }

    private SquareMiniGame.SquareGameData getNewGameData(){
        gameData = SquareMiniGame.getNewGame(2);
        return gameData;
    }
}
