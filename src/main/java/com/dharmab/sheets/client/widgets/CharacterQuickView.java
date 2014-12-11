package com.dharmab.sheets.client.widgets;

import com.dharmab.sheets.client.events.CharacterSelectionEvent;
import com.dharmab.sheets.client.requestfactory.CharacterProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.google.web.bindery.event.shared.EventBus;


public class CharacterQuickView extends Composite implements LeafValueEditor<CharacterProxy> {
    private static CharacterQuickViewUiBinder ourUiBinder = GWT.create(CharacterQuickViewUiBinder.class);
    @UiField
    Panel panel;
    @UiField
    Label name;
    @UiField
    Button edit;

    private EventBus eventBus;
    private CharacterProxy character;
    private Integer id;

    public CharacterQuickView(EventBus eventBus) {
        this.eventBus = eventBus;
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void setValue(CharacterProxy character) {
        this.character = character;
        name.setText(character.getName());
        id = character.getId();
    }

    @Override
    public CharacterProxy getValue() {
        return character;
    }

    interface CharacterQuickViewUiBinder extends UiBinder<HTMLPanel, CharacterQuickView> {
    }

    @UiHandler("edit")
    public void goToCharacterEditor(@SuppressWarnings("UnusedParameters") ClickEvent event) {
        eventBus.fireEvent(new CharacterSelectionEvent(id));
    }
}