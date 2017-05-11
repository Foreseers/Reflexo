package com.foreseer.reflexo.TwoSquareGame;

/**
 * Created by Foreseer on 10/05/2017.
 * For any questions, feel free to reach me using any of my contacts.
 * Contacts:
 * e-mail (preferred): fforeseer@gmail.com
 */

public interface TwoSquareGameModel {
    public void onColorChosen(String color);
    public void onDestroy();
    public void onStart();
    public String getColorName(String hexCode);
    public boolean getResult();

    public class SquareGameData {
        private String[] colorHexCodes;
        private String winningCode;
        private String colorMessage;
        private String[] colorNames;

        public SquareGameData(String[] colorHexCodes, String winningCode, String colorMessage, String[] colorNames) {
            this.colorHexCodes = colorHexCodes;
            this.winningCode = winningCode;
            this.colorMessage = colorMessage;
            this.colorNames = colorNames;
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

        public String[] getColorNames() {
            return colorNames;
        }
    }

    public interface SquareGameModelListener {
        public void onGameDataReceived(SquareGameData data);
        public void onGameFinished(boolean result, long time);
    }
}
