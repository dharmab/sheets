package com.dharmab.sheets.client;

import com.dharmab.sheets.client.inject.AppInjector;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class Sheets implements EntryPoint {
    public void onModuleLoad() {
        AppInjector injector = GWT.create(AppInjector.class);
        PlaceHistoryHandler placeHistoryHandler = injector.getPlaceHistoryHandler();
        ActivityManager activityManager = injector.getActivityManager();

        SimplePanel panel = new SimplePanel();
        activityManager.setDisplay(panel);
        RootPanel.get().add(panel);

        placeHistoryHandler.handleCurrentHistory();
    }
}
