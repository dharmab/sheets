package com.dharmab.sheets.client.presenters;

import com.dharmab.sheets.client.places.CharacterPlace;
import com.dharmab.sheets.client.requestfactory.AppRequestFactory;
import com.dharmab.sheets.client.views.CharacterView;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class CharacterActivity extends AppActivity implements CharacterPresenter {

    private AppRequestFactory requestFactory;
    private CharacterView view;

    @Inject
    public CharacterActivity(@Assisted CharacterPlace place, CharacterView view, AppRequestFactory requestFactory) {
        this.view = view;
        this.requestFactory = requestFactory;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        panel.setWidget(view);
    }
}
