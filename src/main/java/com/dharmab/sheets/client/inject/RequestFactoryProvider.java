package com.dharmab.sheets.client.inject;

import com.dharmab.sheets.client.requestfactory.AppRequestFactory;
import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;

public class RequestFactoryProvider implements Provider<AppRequestFactory> {
    @Inject
    EventBus eventBus;

    @Override
    public AppRequestFactory get() {
        AppRequestFactory requestFactory = GWT.create(AppRequestFactory.class);
        requestFactory.initialize(eventBus);
        return requestFactory;
    }
}
