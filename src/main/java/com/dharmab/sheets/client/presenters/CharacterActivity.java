package com.dharmab.sheets.client.presenters;

import com.dharmab.sheets.client.events.CharacterEditEvent;
import com.dharmab.sheets.client.events.CharacterEditEventHandler;
import com.dharmab.sheets.client.places.CharacterPlace;
import com.dharmab.sheets.client.places.WelcomePlace;
import com.dharmab.sheets.client.rpc.CharacterServiceAsync;
import com.dharmab.sheets.client.views.CharacterView;
import com.dharmab.sheets.client.widgets.CharacterEditor;
import com.dharmab.sheets.shared.character.Character;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.web.bindery.event.shared.EventBus;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;


public class CharacterActivity extends AppActivity implements CharacterPresenter, CharacterEditEventHandler {

    private CharacterView view;
    private Driver driver;
    private CharacterServiceAsync characterService;
    private PlaceController placeController;
    private EventBus eventBus;
    private Integer characterId;
    private Validator validator;

    @Inject
    public CharacterActivity(@Assisted CharacterPlace place,
                             CharacterView view,
                             Driver driver,
                             EventBus eventBus,
                             PlaceController placeController,
                             CharacterServiceAsync characterService,
                             Validator validator) {
        this.view = view;
        this.driver = driver;
        this.placeController = placeController;
        this.eventBus = eventBus;
        this.characterService = characterService;
        this.validator = validator;

        try {
            characterId = Integer.parseInt(place.getToken());
        } catch (NumberFormatException e) {
            goToCharacterNotFoundPlace();
        }

        view.setPresenter(this);
        view.hideErrorMessage();

        driver.initialize(view.asEditor());
    }

    private void refreshCharacter() {
        characterService.get(characterId, new AsyncCallback<Character>() {
            @Override
            public void onFailure(Throwable caught) {
                // todo show error message
            }

            @Override
            public void onSuccess(Character character) {
                if (character == null) {
                    goToCharacterNotFoundPlace();
                }
                driver.edit(character);
            }
        });
    }

    @Override
    public void save() {
        // todo client-side validation
        Character character = driver.flush();
        Set<ConstraintViolation<Character>> violations = validator.validate(character);
        if (violations.isEmpty()) {
            characterService.merge(character, new AsyncCallback<Boolean>() {
                @Override
                public void onFailure(Throwable caught) {
                    view.showErrorMessage("An error occurred");
                }

                @Override
                public void onSuccess(Boolean result) {
                    view.hideErrorMessage();
                    refreshCharacter();
                }
            });
        } else {
            StringBuilder builder = new StringBuilder();
            for (ConstraintViolation<Character> violation : violations) {
                builder
                        .append(violation.getPropertyPath())
                        .append(" ")
                        .append(violation.getMessage())
                        .append("\n");
            }
            view.showErrorMessage(builder.toString());
            refreshCharacter();
        }
    }

    @Override
    public void goToWelcomePlace() {
        placeController.goTo(new WelcomePlace());
    }

    private void goToCharacterNotFoundPlace() {
        // todo go to a 404 page
        goToWelcomePlace();
    }

    @Override
    public void start(AcceptsOneWidget panel) {
        panel.setWidget(view);
        refreshCharacter();
        eventBus.addHandler(CharacterEditEvent.TYPE, this);
    }

    @Override
    public void onCharacterEdit(CharacterEditEvent event) {
        save();
    }

    interface Driver extends SimpleBeanEditorDriver<Character, CharacterEditor> {

    }
}
