package com.dharmab.sheets.client.presenters;

import com.dharmab.sheets.client.events.CharacterDeletionEvent;
import com.dharmab.sheets.client.events.CharacterDeletionEventHandler;
import com.dharmab.sheets.client.events.CharacterSelectionEvent;
import com.dharmab.sheets.client.events.CharacterSelectionEventHandler;
import com.dharmab.sheets.client.places.CharacterPlace;
import com.dharmab.sheets.client.rpc.CharacterServiceAsync;
import com.dharmab.sheets.client.views.WelcomeView;
import com.dharmab.sheets.client.widgets.CharacterListView;
import com.dharmab.sheets.shared.Character.Character;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

import java.util.List;

public class WelcomeActivity extends AppActivity implements WelcomePresenter, CharacterSelectionEventHandler, CharacterDeletionEventHandler {
    private WelcomeView view;
    private PlaceController placeController;
    private Driver driver;
    private CharacterServiceAsync characterService;

    @Inject
    public WelcomeActivity(WelcomeView view,
                           Driver driver,
                           EventBus eventBus,
                           PlaceController placeController,
                           CharacterServiceAsync characterService) {
        this.view = view;
        this.placeController = placeController;
        this.characterService = characterService;
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
        characterService.getAll(new AsyncCallback<List<com.dharmab.sheets.shared.Character.Character>>() {
            @Override
            public void onFailure(Throwable caught) {
                // todo show error message
            }

            @Override
            public void onSuccess(List<Character> result) {
                driver.edit(result);
            }
        });
    }

    @Override
    public void createCharacter() {
        characterService.persist(new Character(), new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                // todo show error
            }

            @Override
            public void onSuccess(Void result) {
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
        characterService.delete(event.getId(), new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                // todo show error message
            }

            @Override
            public void onSuccess(Void result) {
                refreshCharacterList();
            }
        });
    }

    interface Driver extends SimpleBeanEditorDriver<List<Character>, CharacterListView> {

    }
}
