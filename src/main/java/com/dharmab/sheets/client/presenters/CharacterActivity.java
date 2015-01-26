package com.dharmab.sheets.client.presenters;

import com.dharmab.sheets.client.events.CharacterEditEvent;
import com.dharmab.sheets.client.events.CharacterEditEventHandler;
import com.dharmab.sheets.client.places.CharacterPlace;
import com.dharmab.sheets.client.places.WelcomePlace;
import com.dharmab.sheets.client.requestfactory.AppRequestFactory;
import com.dharmab.sheets.client.requestfactory.CharacterProxy;
import com.dharmab.sheets.client.requestfactory.CharacterRequest;
import com.dharmab.sheets.client.views.CharacterView;
import com.dharmab.sheets.client.widgets.CharacterEditor;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import javax.validation.ConstraintViolation;
import java.util.Set;


public class CharacterActivity extends AppActivity implements CharacterPresenter, CharacterEditEventHandler {

    private CharacterProxy character;
    private CharacterView view;
    private Driver driver;
    private AppRequestFactory requestFactory;
    private PlaceController placeController;
    private EventBus eventBus;
    private Integer characterId;

    @Inject
    public CharacterActivity(@Assisted CharacterPlace place,
                             CharacterView view,
                             Driver driver,
                             EventBus eventBus,
                             PlaceController placeController,
                             AppRequestFactory requestFactory) {
        this.view = view;
        this.driver = driver;
        this.placeController = placeController;
        this.eventBus = eventBus;
        this.requestFactory = requestFactory;

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
        requestFactory.getCharacterRequest().get(characterId).fire(new Receiver<CharacterProxy>() {
            @Override
            public void onSuccess(CharacterProxy response) {
                if (response == null) {
                    goToCharacterNotFoundPlace();
                }
                character = response;
                editCharacter();
            }
        });
    }

    private void editCharacter() {
        driver.edit(character, requestFactory.getCharacterRequest());
    }

    @Override
    public void save() {
        CharacterRequest request = (CharacterRequest) driver.flush();
        request.persist(character).fire(new Receiver<Void>() {
            @Override
            public void onFailure(ServerFailure error) {
                view.showErrorMessage("An error occurred");
            }

            @Override
            public void onSuccess(Void response) {
                view.hideErrorMessage();
                refreshCharacter();
            }

            @Override
            public void onConstraintViolation(Set<ConstraintViolation<?>> violations) {
                StringBuilder builder = new StringBuilder();
                for (ConstraintViolation<?> violation : violations) {
                    builder.append(violation.getMessage());
                }
                view.showErrorMessage(builder.toString());
            }
        });
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

    interface Driver extends RequestFactoryEditorDriver<CharacterProxy, CharacterEditor> {

    }
}
