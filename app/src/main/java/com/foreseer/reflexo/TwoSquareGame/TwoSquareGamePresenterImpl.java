package com.foreseer.reflexo.TwoSquareGame;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Foreseer on 10/05/2017.
 * For any questions, feel free to reach me using any of my contacts.
 * Contacts:
 * e-mail (preferred): fforeseer@gmail.com
 */

public class TwoSquareGamePresenterImpl implements TwoSquareGamePresenter, TwoSquareGameModel.SquareGameModelListener{
    private TwoSquareGameView gameView;
    private TwoSquareGameModel gameModel;

    private final int DELAY = 2;

    public TwoSquareGamePresenterImpl(TwoSquareGameView gameView) {
        this.gameView = gameView;
        this.gameModel = new TwoSquareGameModelImpl(this);
    }

    @Override
    public void onColorChosen(String colorHexCode) {
        gameModel.onColorChosen(colorHexCode);
    }

    @Override
    public void onDestroy() {
        gameView = null;
        gameModel.onDestroy();
        gameModel = null;
    }

    @Override
    public void onSquaresShown() {
        gameModel.onStart();
    }

    @Override
    public void onGameDataReceived(TwoSquareGameModel.SquareGameData data) {
        gameView.showChooseColorMessage(data.getColorMessage(), data.getWinningCode());
        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(i -> {
                    if (gameView != null) {
                        gameView.makeMessageInvisible();
                        gameView.setSquareColors(data.getColorHexCodes());
                        gameView.setSquareText(data.getColorHexCodes(), data.getColorNames());
                        gameModel.onStart();
                    }
                });
    }

    @Override
    public void onGameFinished(boolean result, long time) {
        gameView.onResultReceived(result);
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(i -> {
                    if (gameView != null) {
                        gameView.finishGame(time);
                    }
                });
    }
}
