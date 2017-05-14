package com.foreseer.reflexo.FourSquareGame;

import com.foreseer.reflexo.IntUtils;

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

    private SquareGameData gameData;

    public SquareGameModelImpl(SquareGameModelListener listener) {
        this.listener = listener;
        initializeGame();
    }

    public void initializeGame() {
        listener.onGameDataReceived(getNewGameData(getShuffledColorList()));
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
        String[] hexCodes = new String[4];
        for (int i = 0; i < colors.size(); i++) {
            hexCodes[i] = colors.get(i).getHexCode();
        }

        int rand = IntUtils.getRandomNumberInRange(0, 3);
        String colorMessage = "";
        String winningHex = "";
        for (int j = 0; j < colors.size(); j++) {
            if (j != rand){
                continue;
            }
            colorMessage = colors.get(j).getColorName();
            winningHex = colors.get(j).getHexCode();
        }

        gameData = new SquareGameData(hexCodes, winningHex, colorMessage);
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
