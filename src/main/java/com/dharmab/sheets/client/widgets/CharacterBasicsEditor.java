package com.dharmab.sheets.client.widgets;

import com.dharmab.sheets.client.events.CharacterEditEvent;
import com.dharmab.sheets.client.requestfactory.CharacterProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;


public class CharacterBasicsEditor extends Composite implements Editor<CharacterProxy> {
    private static CharacterBasicsEditorUiBinder ourUiBinder = GWT.create(CharacterBasicsEditorUiBinder.class);
    private final EventBus eventBus;
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
    ValueChangeHandler<Integer> integerValueChangeHandler = new ValueChangeHandler<Integer>() {
        @Override
        public void onValueChange(ValueChangeEvent<Integer> event) {
            fireCharacterEditEvent();
        }
    };

    @Inject
    public CharacterBasicsEditor(EventBus eventBus) {
        this.eventBus = eventBus;
        initWidget(ourUiBinder.createAndBindUi(this));
        level.addValueChangeHandler(integerValueChangeHandler);
        experiencePoints.addValueChangeHandler(integerValueChangeHandler);
    }

    @UiHandler(value = {"name", "characterClass", "background", "race"})
    void onStringValueChange(@SuppressWarnings("UnusedParameters") ValueChangeEvent<String> event) {
        fireCharacterEditEvent();
    }

    @UiHandler(value = {"level", "experiencePoints"})
    void onIntegerValueChange(@SuppressWarnings("UnusedParameters") ValueChangeEvent<Integer> event) {
        fireCharacterEditEvent();
    }

    private void fireCharacterEditEvent() {
        eventBus.fireEvent(new CharacterEditEvent());
    }

    interface CharacterBasicsEditorUiBinder extends UiBinder<HTMLPanel, CharacterBasicsEditor> {
    }
}