package com.dharmab.sheets.client.presenters;

import com.dharmab.sheets.client.places.CharacterPlace;
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

import javax.validation.ConstraintViolation;
import java.util.Set;

public class CharacterActivity extends AppActivity implements CharacterPresenter {

    private AppRequestFactory requestFactory;
    private CharacterView view;
    private Driver driver;
    private CharacterProxy character;

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
        driver.initialize(requestFactory, view.asEditor());

        try {
            Integer id = Integer.parseInt(place.getToken());
            requestFactory.characterRequest().get(id).fire(new Receiver<CharacterProxy>() {
                @Override
                public void onSuccess(CharacterProxy character) {
                    edit(character);
                }
            });
        } catch (NumberFormatException e) {
            // todo 404 page
        }

        view.setPresenter(this);
    }

    private void edit(CharacterProxy character) {
        this.character = character;
        AppRequestFactory.CharacterRequest request = requestFactory.characterRequest();
        request.edit(character);
        driver.edit(this.character, request);
        request.persist(character);
    }

    @Override
    public void save() {
        AppRequestFactory.CharacterRequest request = (AppRequestFactory.CharacterRequest) driver.flush();
        request.fire(new Receiver<Void>() {
            @Override
            public void onConstraintViolation(Set<ConstraintViolation<?>> violations) {
                StringBuilder builder = new StringBuilder();
                for (ConstraintViolation<?> violation : violations) {
                    builder.append(violation.getMessage());
                    builder.append("\n");
                }
                view.showErrorMessage(builder.toString());
            }

            @Override
            public void onSuccess(Void response) {
                requestFactory.characterRequest().get(character.getId()).fire(new Receiver<CharacterProxy>() {
                    @Override
                    public void onSuccess(CharacterProxy response) {
                        view.hideErrorMessage();
                        edit(response);
                    }
                });
            }
        });
    }

    @Override
    public void start(AcceptsOneWidget panel) {
        panel.setWidget(view);
    }

    interface Driver extends RequestFactoryEditorDriver<CharacterProxy, CharacterBasicsEditor> {

    }
}
