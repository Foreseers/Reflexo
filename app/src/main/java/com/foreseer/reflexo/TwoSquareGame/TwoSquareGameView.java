package com.foreseer.reflexo.TwoSquareGame;

/**
 * Created by Foreseer on 10/05/2017.
 * For any questions, feel free to reach me using any of my contacts.
 * Contacts:
 * e-mail (preferred): fforeseer@gmail.com
 */

public interface TwoSquareGameView {
    public void onResultReceived(boolean result);
    public void finishGame(long time);
    public void showChooseColorMessage(String message, String colorHex);
    public void makeMessageInvisible();
    public void setSquareColors(String[] colorHexCodes);
    public void setSquareText(String[] colorHexCodes, String[] colorNames);
}
