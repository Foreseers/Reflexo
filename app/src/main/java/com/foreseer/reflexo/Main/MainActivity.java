package com.foreseer.reflexo.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.foreseer.reflexo.R;
import com.foreseer.reflexo.SquareGame.SquareGameActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView{

    private MainPresenter mainPresenter;

    @BindView(R.id.button_startThree)
    Button startThreeButton;

    @BindView(R.id.button_start)
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        startButton.setOnClickListener(v -> mainPresenter.onStartButtonPressed());

        startThreeButton.setOnClickListener(v -> mainPresenter.onStartThreeSeriesButtonPressed());

        mainPresenter = new MainPresenterImpl(this);

    }

    @Override
    public void startSquareActivity() {
        Intent intent = new Intent(this, SquareGameActivity.class);
        startActivityForResult(intent, 1);
        overridePendingTransition(R.anim.fade_in_activity, R.anim.fade_out_activity);
        //finish();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null){
            mainPresenter.gameFinishedNullReceived();
        } else {
            if (requestCode == 1) {
                long longTime = Long.parseLong(data.getStringExtra("reactionTime"));
                mainPresenter.gameFinished(longTime);
            }
        }
    }
}
