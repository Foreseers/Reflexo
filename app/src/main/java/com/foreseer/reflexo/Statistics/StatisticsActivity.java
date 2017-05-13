package com.foreseer.reflexo.Statistics;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

import com.foreseer.reflexo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatisticsActivity extends AppCompatActivity implements StatisticsView{

    private StatisticsPresenter presenter;

    @BindView(R.id.tabHost)
    TabHost tabHost;

    //Four squares statistics elements
    @BindView(R.id.textView_fourSquares_totalTriesNumber)
    TextView textView_fourSquares_totalTriesNumber;

    @BindView(R.id.textView_fourSquares_correctTriesNumber)
    TextView textView_fourSquares_correctTriesNumber;

    @BindView(R.id.textView_fourSquares_incorrectTriesNumber)
    TextView textView_fourSquares_incorrectTriesNumber;

    @BindView(R.id.textView_fourSquares_percentageCorrectTriesNumber)
    TextView textView_fourSquares_percentageCorrectTriesNumber;

    @BindView(R.id.textView_fourSquares_averageReactionTimeNumber)
    TextView textView_fourSquares_averageReactionTimeNumber;

    //Two squares statistics elements
    @BindView(R.id.textView_twoSquares_totalTriesNumber)
    TextView textView_twoSquares_totalTriesNumber;

    @BindView(R.id.textView_twoSquares_correctTriesNumber)
    TextView textView_twoSquares_correctTriesNumber;

    @BindView(R.id.textView_twoSquares_incorrectTriesNumber)
    TextView textView_twoSquares_incorrectTriesNumber;

    @BindView(R.id.textView_twoSquares_percentageCorrectTriesNumber)
    TextView textView_twoSquares_percentageCorrectTriesNumber;

    @BindView(R.id.textView_twoSquares_averageReactionTimeNumber)
    TextView textView_twoSquares_averageReactionTimeNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        ButterKnife.bind(this);

        presenter = new StatisticsPresenterImpl(this);
        presenter.onStart();
    }

    @Override
    public void initializeTabs(){
        tabHost.setup();
        TabHost.TabSpec spec = tabHost.newTabSpec("Two Squares");
        spec.setContent(R.id.tab_statistics_twoSquares);
        spec.setIndicator("Two Squares");
        tabHost.addTab(spec);


        spec = tabHost.newTabSpec("Four Squares");
        spec.setContent(R.id.tab_statistics_fourSquares);
        spec.setIndicator("Four Squares");
        tabHost.addTab(spec);
    }

    @Override
    public void initializeTab(String tabName, String totalTries, String correctTries, String incorrectTries, String percentageCorrect, String averageReactionTime) {
        switch (tabName){
            case ("Two Squares"):
                initializeTab(R.id.tab_statistics_twoSquares, totalTries, correctTries, incorrectTries, percentageCorrect, averageReactionTime);
                break;
            case ("Four Squares"):
                initializeTab(R.id.tab_statistics_fourSquares, totalTries, correctTries, incorrectTries, percentageCorrect, averageReactionTime);
                break;
        }
    }

    private void initializeTab(int tabId, String totalTries, String correctTries, String incorrectTries, String percentageCorrect, String averageReactionTime){
        switch (tabId){
            case (R.id.tab_statistics_fourSquares):
                textView_fourSquares_totalTriesNumber.setText(totalTries);
                textView_fourSquares_correctTriesNumber.setText(correctTries);
                textView_fourSquares_incorrectTriesNumber.setText(incorrectTries);
                textView_fourSquares_percentageCorrectTriesNumber.setText(percentageCorrect);
                textView_fourSquares_averageReactionTimeNumber.setText(averageReactionTime);
                break;
            case (R.id.tab_statistics_twoSquares):
                textView_twoSquares_totalTriesNumber.setText(totalTries);
                textView_twoSquares_correctTriesNumber.setText(correctTries);
                textView_twoSquares_incorrectTriesNumber.setText(incorrectTries);
                textView_twoSquares_percentageCorrectTriesNumber.setText(percentageCorrect);
                textView_twoSquares_averageReactionTimeNumber.setText(averageReactionTime);
                break;
        }
    }

    @Override
    public Activity returnActivity() {
        return this;
    }
}
