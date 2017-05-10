package com.foreseer.reflexo.SquareGame;

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

    public class SquareGameData {
        private String[] colorHexCodes;
        private String winningCode;
        private String colorMessage;

        public SquareGameData(String[] colorHexCodes, String winningCode, String colorMessage) {
            this.colorHexCodes = colorHexCodes;
            this.winningCode = winningCode;
            this.colorMessage = colorMessage;
        }

        public String[] getColorHexCodes() {
            return colorHexCodes;
        }

        public String getWinningCode() {
            return winningCode;
        }

        public String getColorMessage() {
            return colorMessage;
        }
    }

    public interface SquareGameModelListener {
        public void onGameDataReceived(SquareGameData data);
        public void onGameFinished(boolean result, long time);
    }
}
