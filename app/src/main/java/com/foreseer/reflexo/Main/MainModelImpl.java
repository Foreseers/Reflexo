package com.foreseer.reflexo.Main;

/**
 * Created by Foreseer on 10/05/2017.
 * For any questions, feel free to reach me using any of my contacts.
 * Contacts:
 * e-mail (preferred): fforeseer@gmail.com
 */

public class MainModelImpl implements MainModel {
    private MainModelListener mainModelListener;

    public MainModelImpl(MainModelListener mainModelListener) {
        this.mainModelListener = mainModelListener;
    }

    @Override
    public void initiateStart() {
        mainModelListener.onGameStart();
    }
}
