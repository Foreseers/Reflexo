package com.foreseer.reflexo.TwoSquareGame;

/**
 * Created by Foreseer on 10/05/2017.
 * For any questions, feel free to reach me using any of my contacts.
 * Contacts:
 * e-mail (preferred): fforeseer@gmail.com
 */

public interface TwoSquareGamePresenter {
    public void onColorChosen(String colorHexCode);
    public void onDestroy();
    public void onSquaresShown();
}
