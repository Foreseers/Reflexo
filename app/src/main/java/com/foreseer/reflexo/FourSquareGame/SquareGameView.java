package com.foreseer.reflexo.FourSquareGame;

/**
 * Created by Foreseer on 10/05/2017.
 * For any questions, feel free to reach me using any of my contacts.
 * Contacts:
 * e-mail (preferred): fforeseer@gmail.com
 */

public interface SquareGameView {
    public void onResultReceived(boolean result);
    public void finishGame(long time);
    public void showChooseColorMessage(String message, String colorHex);
    public void makeMessageInvisible();
    public void setSquareColors(String[] colorHexCodes);
}
