package com.foreseer.reflexo.FourSquareGame;

import com.foreseer.reflexo.IntUtils;
import com.foreseer.reflexo.MiniGames.SquareMiniGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Foreseer on 10/05/2017.
 * For any questions, feel free to reach me using any of my contacts.
 * Contacts:
 * e-mail (preferred): fforeseer@gmail.com
 */

public class SquareGameModelImpl implements SquareGameModel {
    private SquareGameModelListener listener;

    private Date startDate;

    private SquareMiniGame.SquareGameData gameData;

    public SquareGameModelImpl(SquareGameModelListener listener) {
        this.listener = listener;
        initializeGame();
    }

    public void initializeGame() {
        listener.onGameDataReceived(getNewGameData());
    }

    @Override
    public void onColorChosen(String color) {
        long time = new Date().getTime() - startDate.getTime();
        listener.onGameFinished(color.equals(gameData.getWinningCode()), time);
    }

    @Override
    public void onDestroy() {
        listener = null;
    }

    @Override
    public void onStart() {
        startDate = new Date();
    }

    private SquareMiniGame.SquareGameData getNewGameData(){
        gameData = SquareMiniGame.getNewGame(4);
        return gameData;
    }
}
