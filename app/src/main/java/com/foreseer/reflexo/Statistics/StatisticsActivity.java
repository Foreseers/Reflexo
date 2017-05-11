package com.foreseer.reflexo.Statistics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

import com.foreseer.reflexo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatisticsActivity extends AppCompatActivity implements StatisticsView{

    private StatisticsPresenter presenter;

    @BindView(R.id.tabHost)
    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        ButterKnife.bind(this);

        presenter = new StatisticsPresenterImpl(this);

        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("Two Squares");
        spec.setContent(R.id.tab_twoSquares);
        spec.setIndicator("Two Squares");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Four Squares");
        spec.setContent(R.id.tab_fourSquares);
        spec.setIndicator("Four Squares");
        tabHost.addTab(spec);
    }


}
