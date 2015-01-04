package com.dharmab.sheets.client.widgets;

import com.dharmab.sheets.client.events.CharacterDeletionEvent;
import com.dharmab.sheets.client.events.CharacterSelectionEvent;
import com.dharmab.sheets.shared.character.Character;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.web.bindery.event.shared.EventBus;


public class CharacterQuickView extends Composite implements LeafValueEditor<Character> {
    private static CharacterQuickViewUiBinder ourUiBinder = GWT.create(CharacterQuickViewUiBinder.class);
    @UiField
    Label name;
    @UiField
    Button edit;
    @UiField
    Button delete;

    private EventBus eventBus;
    private Character character;
    private Integer id;

    public CharacterQuickView(EventBus eventBus) {
        this.eventBus = eventBus;
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public Character getValue() {
        return character;
    }

    @Override
    public void setValue(Character character) {
        this.character = character;
        name.setText(character.getName());
        id = character.getId();
    }

    @UiHandler("edit")
    public void goToCharacterEditor(@SuppressWarnings("UnusedParameters") ClickEvent event) {
        eventBus.fireEvent(new CharacterSelectionEvent(id));
    }

    @UiHandler("delete")
    public void deleteCharacter(@SuppressWarnings("UnusedParameters") ClickEvent event) {
        eventBus.fireEvent(new CharacterDeletionEvent(id));
    }

    interface CharacterQuickViewUiBinder extends UiBinder<HTMLPanel, CharacterQuickView> {
    }
}