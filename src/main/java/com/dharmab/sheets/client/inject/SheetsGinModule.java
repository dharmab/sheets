package com.dharmab.sheets.client.inject;

import com.dharmab.sheets.client.AppActivityMapper;
import com.dharmab.sheets.client.places.AppPlaceHistoryMapper;
import com.dharmab.sheets.client.places.WelcomePlace;
import com.dharmab.sheets.client.presenters.CharacterActivity;
import com.dharmab.sheets.client.presenters.CharacterPresenter;
import com.dharmab.sheets.client.presenters.WelcomeActivity;
import com.dharmab.sheets.client.presenters.WelcomePresenter;
import com.dharmab.sheets.client.requestfactory.AppRequestFactory;
import com.dharmab.sheets.client.views.CharacterView;
import com.dharmab.sheets.client.views.CharacterViewImpl;
import com.dharmab.sheets.client.views.WelcomeView;
import com.dharmab.sheets.client.views.WelcomeViewImpl;
import com.dharmab.sheets.client.widgets.CharacterBasicsEditor;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class SheetsGinModule extends AbstractGinModule {

    @Override
    protected void configure() {
        bind(EventBus.class).to(SimpleEventBus.class).asEagerSingleton();

        bind(ActivityMapper.class).to(AppActivityMapper.class).asEagerSingleton();

        // Views (singletons due to expense of construction)
        bind(WelcomeView.class).to(WelcomeViewImpl.class).asEagerSingleton();
        bind(CharacterView.class).to(CharacterViewImpl.class).asEagerSingleton();

        // Factories for presenters
        install(new GinFactoryModuleBuilder()
                .implement(CharacterPresenter.class, CharacterActivity.class)
                .build(CharacterPresenterFactory.class));
        install(new GinFactoryModuleBuilder()
                .implement(WelcomePresenter.class, WelcomeActivity.class)
                .build(WelcomePresenterFactory.class));
    }

    @Provides
    @Singleton
    PlaceController providePlaceController(EventBus eventBus) {
        return new PlaceController(eventBus);
    }

    @Provides
    @Singleton
    PlaceHistoryHandler providePlaceHistoryHandler(AppPlaceHistoryMapper placeHistoryMapper, PlaceController placeController, EventBus eventBus) {
        PlaceHistoryHandler placeHistoryHandler = new PlaceHistoryHandler(placeHistoryMapper);
        placeHistoryHandler.register(placeController, eventBus, new WelcomePlace());
        return placeHistoryHandler;
    }

    @Provides
    @Singleton
    ActivityManager provideActivityManager(ActivityMapper activityMapper, EventBus eventBus) {
        return new ActivityManager(activityMapper, eventBus);
    }

    @Provides
    @Singleton
    AppRequestFactory provideRequestFactory(EventBus eventBus) {
        AppRequestFactory requestFactory = GWT.create(AppRequestFactory.class);
        requestFactory.initialize(eventBus);
        return requestFactory;
    }

    @Provides
    CharacterBasicsEditor provideCharacterEditor(EventBus eventBus) {
        return new CharacterBasicsEditor(eventBus);
    }
}
