package com.dharmab.sheets.client.views;

import com.dharmab.sheets.client.presenters.CharacterPresenter;
import com.dharmab.sheets.client.widgets.CharacterBasicsEditor;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;


public class CharacterViewImpl extends Composite implements CharacterView, IsEditor<CharacterBasicsEditor> {
    private static CharacterViewUiBinder ourUiBinder = GWT.create(CharacterViewUiBinder.class);
    @UiField(provided = true)
    CharacterBasicsEditor characterEditor;
    @UiField
    Button back;
    @UiField
    Label errorMessageLabel;

    private CharacterPresenter presenter;

    @Inject
    public CharacterViewImpl(CharacterBasicsEditor characterEditor) {
        this.characterEditor = characterEditor;
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public CharacterBasicsEditor asEditor() {
        return characterEditor;
    }

    @Override
    public void setPresenter(CharacterPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showErrorMessage(String message) {
        errorMessageLabel.setText(message);
        errorMessageLabel.setVisible(true);
    }

    @Override
    public void hideErrorMessage() {
        errorMessageLabel.setText("");
        errorMessageLabel.setVisible(false);
    }

    @UiHandler("back")
    public void goBack(@SuppressWarnings("UnusedParameters") ClickEvent event) {
        presenter.goToWelcomePlace();
    }

    interface CharacterViewUiBinder extends UiBinder<HTMLPanel, CharacterViewImpl> {
    }
}