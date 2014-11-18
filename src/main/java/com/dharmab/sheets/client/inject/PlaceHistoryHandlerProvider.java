package com.dharmab.sheets.client.inject;

import com.dharmab.sheets.client.places.AppPlaceHistoryMapper;
import com.dharmab.sheets.client.places.WelcomePlace;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

import javax.inject.Provider;

public class PlaceHistoryHandlerProvider implements Provider<PlaceHistoryHandler> {
    @Inject
    private PlaceController placeController;
    @Inject
    private EventBus eventBus;

    @Override
    public PlaceHistoryHandler get() {
        PlaceHistoryMapper placeHistoryMapper = GWT.create(AppPlaceHistoryMapper.class);
        PlaceHistoryHandler placeHistoryHandler = new PlaceHistoryHandler(placeHistoryMapper);
        placeHistoryHandler.register(placeController, eventBus, new WelcomePlace());
        return placeHistoryHandler;
    }
}
