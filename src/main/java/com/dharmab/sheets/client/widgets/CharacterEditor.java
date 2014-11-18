package com.dharmab.sheets.client.widgets;

import com.dharmab.sheets.client.requestfactory.CharacterProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;


public class CharacterEditor extends Composite implements Editor<CharacterProxy> {
    private static CharacterEditorUiBinder ourUiBinder = GWT.create(CharacterEditorUiBinder.class);
    @UiField
    TextBox name;
    @UiField
    TextBox background;

    public CharacterEditor() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    interface CharacterEditorUiBinder extends UiBinder<HTMLPanel, CharacterEditor> {
    }
}