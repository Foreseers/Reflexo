package com.foreseer.reflexo.FourSquareGame;

import com.foreseer.reflexo.MiniGames.SquareMiniGame;

/**
 * Created by Foreseer on 10/05/2017.
 * For any questions, feel free to reach me using any of my contacts.
 * Contacts:
 * e-mail (preferred): fforeseer@gmail.com
 */

public interface SquareGameModel {
    public void onColorChosen(String color);
    public void onDestroy();
    public void onStart();

    public interface SquareGameModelListener {
        public void onGameDataReceived(SquareMiniGame.SquareGameData data);
        public void onGameFinished(boolean result, long time);
    }
}
