package com.dharmab.sheets.client.inject;

import com.dharmab.sheets.client.requestfactory.AppRequestFactory;
import com.dharmab.sheets.client.views.CharacterView;
import com.dharmab.sheets.client.views.WelcomeView;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.web.bindery.event.shared.EventBus;

@GinModules(SheetsGinModule.class)
public interface AppInjector extends Ginjector {
    AppRequestFactory getRequestFactory();

    WelcomeView getWelcomeView();

    CharacterView getCharacterView();

    EventBus getEventBus();

    PlaceController getPlaceController();

    PlaceHistoryHandler getPlaceHistoryHandler();

    ActivityManager getActivityManager();
}
