package com.dharmab.sheets.client.views;

import com.dharmab.sheets.client.presenters.CharacterPresenter;
import com.dharmab.sheets.client.widgets.CharacterEditor;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;


public class CharacterViewImpl extends Composite implements CharacterView, IsEditor<CharacterEditor> {
    private static CharacterViewUiBinder ourUiBinder = GWT.create(CharacterViewUiBinder.class);
    @UiField
    CharacterEditor characterEditor;
    private CharacterPresenter presenter;

    public CharacterViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public CharacterEditor asEditor() {
        return characterEditor;
    }

    @Override
    public void setPresenter(CharacterPresenter presenter) {
        this.presenter = presenter;
    }

    interface CharacterViewUiBinder extends UiBinder<HTMLPanel, CharacterViewImpl> {
    }
}