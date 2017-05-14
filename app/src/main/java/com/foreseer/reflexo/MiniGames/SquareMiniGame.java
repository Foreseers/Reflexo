package com.foreseer.reflexo.MiniGames;

import com.foreseer.reflexo.IntUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Foreseer on 14/05/2017.
 * For any questions, feel free to reach me using any of my contacts.
 * Contacts:
 * e-mail (preferred): fforeseer@gmail.com
 */

public class SquareMiniGame {

    private SquareMiniGame() {

    }

    public static SquareGameData getNewGame(int amountOfSquares){
        return initializeGame(amountOfSquares);
    }

    private static SquareGameData initializeGame(int amount) {
        return getNewGameData(getShuffledColorList(), amount);
    }

    private static List<Color> getShuffledColorList(){
        List<Color> colors = new ArrayList<>();
        colors.add(new Color("#FFFF00", "YELLOW"));
        colors.add(new Color("#00FF00", "GREEN"));
        colors.add(new Color("#FF0000", "RED"));
        colors.add(new Color("#0000FF", "BLUE"));

        Collections.shuffle(colors);
        return colors;
    }

    private static Map<String, String> fillColourMap(){

        Map<String, String> colorHexNames = new HashMap<>();

        colorHexNames.put("#FFFF00", "YELLOW");
        colorHexNames.put("#00FF00", "GREEN");
        colorHexNames.put("#FF0000", "RED");
        colorHexNames.put("#0000FF", "BLUE");

        return colorHexNames;
    }


    private static SquareGameData getNewGameData(List<Color> colors, int amount){
        Map<String, String> colorHexNames = fillColourMap();

        String[] hexCodes = new String[amount];
        for (int i = 0; i < amount; i++) {
            hexCodes[i] = colors.get(i).getHexCode();
        }

        int rand = IntUtils.getRandomNumberInRange(0, amount - 1);
        String colorMessage = "";
        String winningHex = "";
        colorMessage = colors.get(rand).getColorName();
        winningHex = colors.get(rand).getHexCode();

        String[] colorNames = new String[amount];
        for (int i = 0; i < hexCodes.length; i++) {
            colorNames[i] = colorHexNames.get(hexCodes[i]);
        }

        SquareGameData gameData = new SquareGameData(hexCodes, winningHex, colorMessage, colorNames);
        return gameData;
    }

    private static class Color {
        private String hexCode;
        private String colorName;

        public Color(String hexCode, String colorName) {
            this.hexCode = hexCode;
            this.colorName = colorName;
        }

        public String getHexCode() {
            return hexCode;
        }

        public String getColorName() {
            return colorName;
        }
    }

    public static class SquareGameData {
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
}
