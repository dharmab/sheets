package com.dharmab.sheets.client.inject;

import com.dharmab.sheets.client.AppActivityMapper;
import com.dharmab.sheets.client.presenters.CharacterActivity;
import com.dharmab.sheets.client.presenters.CharacterPresenter;
import com.dharmab.sheets.client.presenters.WelcomeActivity;
import com.dharmab.sheets.client.presenters.WelcomePresenter;
import com.dharmab.sheets.client.requestfactory.AppRequestFactory;
import com.dharmab.sheets.client.views.CharacterView;
import com.dharmab.sheets.client.views.CharacterViewImpl;
import com.dharmab.sheets.client.views.WelcomeView;
import com.dharmab.sheets.client.views.WelcomeViewImpl;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class SheetsGinModule extends AbstractGinModule {
    @Override
    protected void configure() {
        // Event Bus
        bind(EventBus.class).to(SimpleEventBus.class).asEagerSingleton();

        // Activity and Place controllers (required for initialization)
        bind(PlaceController.class).toProvider(PlaceControllerProvider.class).asEagerSingleton();
        bind(PlaceHistoryHandler.class).toProvider(PlaceHistoryHandlerProvider.class);
        bind(ActivityMapper.class).to(AppActivityMapper.class).asEagerSingleton();
        bind(ActivityManager.class).toProvider(ActivityManagerProvider.class).asEagerSingleton();

        // RequestFactory (required for remote calls)
        bind(AppRequestFactory.class).toProvider(RequestFactoryProvider.class).asEagerSingleton();

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
}
