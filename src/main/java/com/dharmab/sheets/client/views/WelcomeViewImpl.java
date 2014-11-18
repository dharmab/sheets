package com.dharmab.sheets.client.views;

import com.dharmab.sheets.client.presenters.WelcomePresenter;
import com.dharmab.sheets.client.widgets.CharacterListView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;


public class WelcomeViewImpl extends Composite implements WelcomeView {
    private static WelcomeViewImplUiBinder ourUiBinder = GWT.create(WelcomeViewImplUiBinder.class);
    @UiField(provided = true)
    CharacterListView characterList;
    @UiField
    Button create;
    private WelcomePresenter presenter;

    @Inject
    public WelcomeViewImpl(EventBus eventBus) {
        characterList = new CharacterListView(eventBus);
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void setPresenter(WelcomePresenter presenter) {
        this.presenter = presenter;
    }


    @UiHandler("create")
    public void handleClick(@SuppressWarnings("UnusedParameters") ClickEvent event) {
        presenter.createCharacter();
    }

    @Override
    public CharacterListView asEditor() {
        return characterList;
    }

    interface WelcomeViewImplUiBinder extends UiBinder<HTMLPanel, WelcomeViewImpl> {
    }
}