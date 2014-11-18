package com.dharmab.sheets.client.presenters;

import com.dharmab.sheets.client.events.CharacterSelectionEvent;
import com.dharmab.sheets.client.events.CharacterSelectionEventHandler;
import com.dharmab.sheets.client.places.CharacterPlace;
import com.dharmab.sheets.client.requestfactory.AppRequestFactory;
import com.dharmab.sheets.client.requestfactory.CharacterProxy;
import com.dharmab.sheets.client.views.WelcomeView;
import com.dharmab.sheets.client.widgets.CharacterListView;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.Receiver;

import java.util.List;

public class WelcomeActivity extends AppActivity implements WelcomePresenter, CharacterSelectionEventHandler {
    private AppRequestFactory requestFactory;
    private WelcomeView view;
    private PlaceController placeController;
    private Driver driver;

    @Inject
    public WelcomeActivity(WelcomeView view,
                           AppRequestFactory requestFactory,
                           Driver driver,
                           EventBus eventBus,
                           PlaceController placeController) {
        this.requestFactory = requestFactory;
        this.view = view;
        this.placeController = placeController;
        this.driver = driver;
        driver.initialize(eventBus, requestFactory, view.asEditor());
        view.setPresenter(this);
    }

    @Override
    public void start(AcceptsOneWidget panel, com.google.gwt.event.shared.EventBus eventBus) {
        panel.setWidget(view);
        refreshCharacterList();
    }

    private void refreshCharacterList() {
        requestFactory.characterRequest().getAll().fire(new Receiver<List<CharacterProxy>>() {
            @Override
            public void onSuccess(List<CharacterProxy> characters) {
                driver.edit(characters, requestFactory.characterRequest());
            }
        });
    }

    @Override
    public void createCharacter() {
        AppRequestFactory.CharacterRequest request = requestFactory.characterRequest();
        CharacterProxy newCharacter = request.create(CharacterProxy.class);
        request.persist(newCharacter).fire(new Receiver<Void>() {
            @Override
            public void onSuccess(Void response) {
                refreshCharacterList();
            }
        });

    }

    @Override
    public void onCharacterSelection(CharacterSelectionEvent event) {
        placeController.goTo(new CharacterPlace(String.valueOf(event.getId())));
    }

    interface Driver extends RequestFactoryEditorDriver<List<CharacterProxy>, CharacterListView> {

    }
}
