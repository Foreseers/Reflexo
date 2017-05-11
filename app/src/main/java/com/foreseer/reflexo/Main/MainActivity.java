package com.foreseer.reflexo.Main;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import com.foreseer.reflexo.R;
import com.foreseer.reflexo.FourSquareGame.SquareGameActivity;
import com.foreseer.reflexo.TwoSquareGame.TwoSquareGameActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView, GameChooseFragment.OnGameChooseFragmentInteractionListener,
        SeriesChooseFragment.OnSeriesChooseFragmentInteractionListener{

    private MainPresenter mainPresenter;

    /*@BindView(R.id.button_startThree)
    Button startThreeButton;*/

    @BindView(R.id.button_start)
    Button startButton;

    @BindView(R.id.main_grid_layout)
    GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        startButton.setOnClickListener(v -> mainPresenter.onStartButtonPressed());

        //startThreeButton.setOnClickListener(v -> mainPresenter.onStartThreeSeriesButtonPressed());

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
    public void startTwoSquareActivity() {
        Intent intent = new Intent(this, TwoSquareGameActivity.class);
        startActivityForResult(intent, 1);
        overridePendingTransition(R.anim.fade_in_activity, R.anim.fade_out_activity);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showGameChoosingFragment() {
        Fragment fragment = GameChooseFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_grid_layout, fragment, "gameChoose");
        fragmentTransaction.commit();
    }

    @Override
    public void removeGameChoosingFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .remove(getSupportFragmentManager().findFragmentByTag("gameChoose"))
                .commit();
    }

    @Override
    public void showSeriesChoosingFragment() {
        Fragment fragment = SeriesChooseFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_grid_layout, fragment, "seriesChoose");
        fragmentTransaction.commit();
    }

    @Override
    public void removeSeriesChoosingFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .remove(getSupportFragmentManager().findFragmentByTag("seriesChoose"))
                .commit();
    }

    @Override
    public void hideStartButton() {
        startButton.setVisibility(View.GONE);
    }

    @Override
    public void showStartButton() {
        startButton.setVisibility(View.VISIBLE);
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

    @Override
    public void onGameSelected(String gameName) {
        mainPresenter.onGameChosen(gameName);
    }

    @Override
    public void onSeriesChosen(String seriesChosen) {
        mainPresenter.onSeriesChosen(seriesChosen);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag("gameChoose") != null){
            mainPresenter.onBackButtonPressed("gameChoose");
        } else if (getSupportFragmentManager().findFragmentByTag("seriesChoose") != null){
            mainPresenter.onBackButtonPressed("seriesChoose");
        } else {
            super.onBackPressed();
        }
    }
}
