package com.foreseer.reflexo.TwoSquareGame;

import com.foreseer.reflexo.IntUtils;

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

    private SquareGameData gameData;

    private Map<String, String> colorHexNames;

    public TwoSquareGameModelImpl(SquareGameModelListener listener) {
        this.listener = listener;
        fillColourMap();
        initializeGame();
    }

    public void initializeGame() {
        listener.onGameDataReceived(getNewGameData(getShuffledColorList()));
    }

    private void fillColourMap(){
        colorHexNames = new HashMap<>();

        colorHexNames.put("#FFFF00", "Yellow");
        colorHexNames.put("#00FF00", "Green");
        colorHexNames.put("#FF0000", "Red");
        colorHexNames.put("#0000FF", "Blue");
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

    @Override
    public String getColorName(String hexCode) {
        return colorHexNames.get(hexCode);
    }

    private List<Color> getShuffledColorList(){
        List<Color> colors = new ArrayList<>();
        colors.add(new Color("#FFFF00", "Yellow"));
        colors.add(new Color("#00FF00", "Green"));
        colors.add(new Color("#FF0000", "Red"));
        colors.add(new Color("#0000FF", "Blue"));

        Collections.shuffle(colors);
        return colors;
    }

    private SquareGameData getNewGameData(List<Color> colors){
        String[] hexCodes = new String[2];
        for (int i = 0; i < 2; i++) {
            hexCodes[i] = colors.get(i).getHexCode();
        }

        int rand = IntUtils.getRandomNumberInRange(0, 1);
        String colorMessage = "";
        String winningHex = "";
        colorMessage = colors.get(rand).getColorName();
        winningHex = colors.get(rand).getHexCode();

        String[] colorNames = new String[2];
        for (int i = 0; i < hexCodes.length; i++) {
            colorNames[i] = colorHexNames.get(hexCodes[i]);
        }

        gameData = new SquareGameData(hexCodes, winningHex, colorMessage, colorNames);
        return gameData;
    }


    private class Color {
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
}
