package com.dharmab.sheets.client.widgets;

import com.dharmab.sheets.client.requestfactory.CharacterProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;


public class CharacterBasicsEditor extends Composite implements Editor<CharacterProxy> {
    private static CharacterBasicsEditorUiBinder ourUiBinder = GWT.create(CharacterBasicsEditorUiBinder.class);
    @UiField
    TextBox name;
    @UiField
    TextBox characterClass;
    @UiField
    IntegerSpinner level;
    @UiField
    TextBox background;
    @UiField
    TextBox race;
    @UiField
    IntegerSpinner experiencePoints;

    public CharacterBasicsEditor() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    interface CharacterBasicsEditorUiBinder extends UiBinder<HTMLPanel, CharacterBasicsEditor> {
    }
}