package com.dharmab.sheets.client.presenters;

import com.dharmab.sheets.client.places.CharacterPlace;
import com.dharmab.sheets.client.places.WelcomePlace;
import com.dharmab.sheets.client.requestfactory.AppRequestFactory;
import com.dharmab.sheets.client.requestfactory.CharacterProxy;
import com.dharmab.sheets.client.views.CharacterView;
import com.dharmab.sheets.client.widgets.CharacterBasicsEditor;
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

public class CharacterActivity extends AppActivity implements CharacterPresenter {

    private AppRequestFactory requestFactory;
    private CharacterView view;
    private Driver driver;
    private CharacterProxy character;
    private PlaceController placeController;

    @Inject
    public CharacterActivity(@Assisted CharacterPlace place,
                             CharacterView view,
                             AppRequestFactory requestFactory,
                             Driver driver,
                             EventBus eventBus,
                             PlaceController placeController) {
        this.view = view;
        this.requestFactory = requestFactory;
        this.driver = driver;
        this.placeController = placeController;

        driver.initialize(requestFactory, view.asEditor());

        try {
            Integer id = Integer.parseInt(place.getToken());
            edit(id);
        } catch (NumberFormatException e) {
            goToCharacterNotFoundPlace();
        }

        view.setPresenter(this);
        view.hideErrorMessage();
    }

    private void edit(CharacterProxy character) {
        this.character = character;
        AppRequestFactory.CharacterRequest request = requestFactory.characterRequest();
        request.edit(character);
        driver.edit(character, request);
    }

    private void edit(Integer characterId) {
        requestFactory.characterRequest().get(characterId).fire(new Receiver<CharacterProxy>() {
            @Override
            public void onSuccess(CharacterProxy character) {
                if (character == null) {
                    goToCharacterNotFoundPlace();
                }
                edit(character);
            }
        });
    }

    @Override
    public void save() {
        ((AppRequestFactory.CharacterRequest) driver.flush()).persist(character).fire(new Receiver<Void>() {
            @Override
            public void onConstraintViolation(Set<ConstraintViolation<?>> violations) {
                StringBuilder builder = new StringBuilder();
                for (ConstraintViolation<?> violation : violations) {
                    builder.append(violation.getMessage());
                    builder.append("\n");
                }
                view.showErrorMessage(builder.toString());
                edit(character.getId());
            }

            @Override
            public void onSuccess(Void response) {
                view.hideErrorMessage();
                edit(character.getId());
            }

            @Override
            public void onFailure(ServerFailure error) {
                view.showErrorMessage("An error occurred: " + error.getMessage());
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
    }

    interface Driver extends RequestFactoryEditorDriver<CharacterProxy, CharacterBasicsEditor> {

    }
}
