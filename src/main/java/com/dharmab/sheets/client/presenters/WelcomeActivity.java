package com.dharmab.sheets.client.presenters;

import com.dharmab.sheets.client.events.CharacterDeletionEvent;
import com.dharmab.sheets.client.events.CharacterDeletionEventHandler;
import com.dharmab.sheets.client.events.CharacterSelectionEvent;
import com.dharmab.sheets.client.events.CharacterSelectionEventHandler;
import com.dharmab.sheets.client.places.CharacterPlace;
import com.dharmab.sheets.client.requestfactory.AppRequestFactory;
import com.dharmab.sheets.client.requestfactory.CharacterProxy;
import com.dharmab.sheets.client.requestfactory.CharacterRequest;
import com.dharmab.sheets.client.views.WelcomeView;
import com.dharmab.sheets.client.widgets.CharacterListView;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.Receiver;

import java.util.List;

public class WelcomeActivity extends AppActivity implements WelcomePresenter, CharacterSelectionEventHandler, CharacterDeletionEventHandler {
    private WelcomeView view;
    private PlaceController placeController;
    private Driver driver;
    private AppRequestFactory requestFactory;

    @Inject
    public WelcomeActivity(WelcomeView view,
                           Driver driver,
                           EventBus eventBus,
                           PlaceController placeController,
                           AppRequestFactory requestFactory) {
        this.view = view;
        this.placeController = placeController;
        this.requestFactory = requestFactory;
        eventBus.addHandler(CharacterSelectionEvent.TYPE, this);
        eventBus.addHandler(CharacterDeletionEvent.TYPE, this);
        this.driver = driver;
        driver.initialize(view.asEditor());
        view.setPresenter(this);
    }

    @Override
    public void start(AcceptsOneWidget panel) {
        panel.setWidget(view);
        refreshCharacterList();
    }

    private void refreshCharacterList() {
        requestFactory.getCharacterRequest().get(0, 1000).fire(new Receiver<List<CharacterProxy>>() {
            @Override
            public void onSuccess(List<CharacterProxy> response) {
                driver.edit(response, requestFactory.getCharacterRequest());
            }
        });
    }

    @Override
    public void createCharacter() {
        CharacterRequest request = requestFactory.getCharacterRequest();
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

    @Override
    public void onCharacterDeletion(CharacterDeletionEvent event) {
        requestFactory.getCharacterRequest().delete(event.getId()).fire(new Receiver<Void>() {
            @Override
            public void onSuccess(Void response) {
                refreshCharacterList();
            }
        });
    }

    interface Driver extends RequestFactoryEditorDriver<List<CharacterProxy>, CharacterListView> {

    }
}
