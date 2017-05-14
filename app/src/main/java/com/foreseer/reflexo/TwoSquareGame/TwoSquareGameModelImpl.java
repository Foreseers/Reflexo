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

    private boolean result;

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

    private List<Color> getShuffledColorList(){
        List<Color> colors = new ArrayList<>();
        colors.add(new Color("#FFFF00", "YELLOW"));
        colors.add(new Color("#00FF00", "GREEN"));
        colors.add(new Color("#FF0000", "RED"));
        colors.add(new Color("#0000FF", "BLUE"));

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
