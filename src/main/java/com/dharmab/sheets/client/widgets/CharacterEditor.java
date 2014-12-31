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
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CharacterEditor extends Composite implements Editor<CharacterProxy> {
    private static CharacterEditorUiBinder ourUiBinder = GWT.create(CharacterEditorUiBinder.class);
    private final EventBus eventBus;
    /**
     * List of integer fields that are annotated as @NotNull in the model, used in a workaround for NPEs
     */
    private final List<HasValue<Integer>> nonNullableIntegerFields;
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
    @UiField
    IntegerSpinner strength;
    @UiField
    NumberLabel<Integer> strengthModifier;
    @UiField
    IntegerSpinner dexterity;
    @UiField
    NumberLabel<Integer> dexterityModifier;
    @UiField
    IntegerSpinner constitution;
    @UiField
    NumberLabel<Integer> constitutionModifier;
    @UiField
    IntegerSpinner intelligence;
    @UiField
    NumberLabel<Integer> intelligenceModifier;
    @UiField
    IntegerSpinner wisdom;
    @UiField
    NumberLabel<Integer> wisdomModifier;
    @UiField
    IntegerSpinner charisma;
    @UiField
    NumberLabel<Integer> charismaModifier;

    ValueChangeHandler<Integer> integerValueChangeHandler = new ValueChangeHandler<Integer>() {
        @Override
        public void onValueChange(ValueChangeEvent<Integer> event) {
            fireCharacterEditEvent();
        }
    };

    @Inject
    public CharacterEditor(EventBus eventBus) {
        this.eventBus = eventBus;
        initWidget(ourUiBinder.createAndBindUi(this));
        level.addValueChangeHandler(integerValueChangeHandler);
        experiencePoints.addValueChangeHandler(integerValueChangeHandler);
        nonNullableIntegerFields = getNonNullableIntegerFields();
    }

    private List<HasValue<Integer>> getNonNullableIntegerFields() {
        List<HasValue<Integer>> list = new ArrayList<>();
        list.addAll(Arrays.asList(level, experiencePoints));
        return list;
    }

    @UiHandler(value = {"name", "characterClass", "background", "race"})
    void onStringValueChange(@SuppressWarnings("UnusedParameters") ValueChangeEvent<String> event) {
        fireCharacterEditEvent();
    }

    @UiHandler(value = {
            "level",
            "experiencePoints",
            "strength",
            "dexterity",
            "constitution",
            "intelligence",
            "wisdom",
            "charisma"
    })
    void onIntegerValueChange(@SuppressWarnings("UnusedParameters") ValueChangeEvent<Integer> event) {
        /*
         If an Integer annotated @NotNull in the backing model is null here, an NPE will throw under certain circumstances
         (e.g. user enters text instead of an integer, or during a page reload). The correct behavior should be to
         indicate the invalid input to the user and not fire any events until the input is corrected.
          */
        for (HasValue<Integer> editor : nonNullableIntegerFields) {
            if (editor.getValue() == null) {
                return;
            }
        }
        fireCharacterEditEvent();
    }

    private void fireCharacterEditEvent() {
        eventBus.fireEvent(new CharacterEditEvent());
    }

    interface CharacterEditorUiBinder extends UiBinder<HTMLPanel, CharacterEditor> {
    }
}