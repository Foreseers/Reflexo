package com.foreseer.reflexo.TwoSquareGame;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.foreseer.reflexo.FourSquareGame.SquareGamePresenter;
import com.foreseer.reflexo.FourSquareGame.SquareGamePresenterImpl;
import com.foreseer.reflexo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TwoSquareGameActivity extends AppCompatActivity implements TwoSquareGameView{

    private TwoSquareGamePresenterImpl presenter;

    @BindView(R.id.button_square1)
    Button button_firstSquare;

    @BindView(R.id.button_square2)
    Button button_secondSquare;

    @BindView(R.id.textView_colorMessage)
    TextView textView_colorMessage;

    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_square_game);

        unbinder = ButterKnife.bind(this);

        button_firstSquare.setOnClickListener(v -> onSquareTapped(v));
        button_secondSquare.setOnClickListener(v -> onSquareTapped(v));

        this.presenter = new TwoSquareGamePresenterImpl(this);
    }

    public void onSquareTapped(View view){
        int color = ((ColorDrawable) view.getBackground()).getColor();
        String hexColor = String.format("#%06X", (0xFFFFFF & color));
        presenter.onColorChosen(hexColor);
    }

    @Override
    public void setSquareColors(String[] colorHexCodes) {
        button_firstSquare.setVisibility(View.VISIBLE);
        button_firstSquare.setBackgroundColor(Color.parseColor(colorHexCodes[0]));

        button_secondSquare.setVisibility(View.VISIBLE);
        button_secondSquare.setBackgroundColor(Color.parseColor(colorHexCodes[1]));
    }

    @Override
    public void setSquareText(String[] colorHexCodes, String[] colorNames) {
        button_firstSquare.setText(colorNames[0]);
        button_firstSquare.setTextColor(Color.parseColor(colorHexCodes[0]));

        button_secondSquare.setText(colorNames[1]);
        button_secondSquare.setTextColor(Color.parseColor(colorHexCodes[1]));
    }

    @Override
    public void showChooseColorMessage(String message, String colorHex) {
        showSquareGameMessage("Choose the square with " + message + " color.");
        //Due to problems with readability..
        //textView_colorMessage.setTextColor(Color.parseColor(colorHex));
    }

    private void showSquareGameMessage(String message) {
        button_firstSquare.setVisibility(View.GONE);
        button_secondSquare.setVisibility(View.GONE);

        textView_colorMessage.setVisibility(View.VISIBLE);
        textView_colorMessage.setText(message);
    }

    @Override
    public void makeMessageInvisible(){
        textView_colorMessage.setVisibility(View.GONE);
    }

    @Override
    public void onResultReceived(boolean result) {
        if (result){
            showSquareGameMessage("Correct!");
        } else {
            showSquareGameMessage("Incorrect!");
        }
    }

    @Override
    public void finishGame(long time, boolean correct) {
        Intent intent = new Intent();
        intent.putExtra("reactionTime", String.valueOf(time));
        intent.putExtra("result", correct);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        presenter.onDestroy();
        super.onDestroy();
    }
}
