package com.dharmab.sheets.client.inject;

import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

import javax.inject.Provider;

public class PlaceControllerProvider implements Provider<PlaceController> {
    @Inject
    EventBus eventBus;

    @Override
    public PlaceController get() {
        return new PlaceController(eventBus);
    }
}
