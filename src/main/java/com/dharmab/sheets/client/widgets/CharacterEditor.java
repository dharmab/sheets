package com.dharmab.sheets.client.widgets;

import com.dharmab.sheets.client.events.CharacterEditEvent;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;


public class CharacterEditor extends Composite implements Editor<com.dharmab.sheets.shared.character.Character> {
    private static CharacterEditorUiBinder ourUiBinder = GWT.create(CharacterEditorUiBinder.class);
    private final EventBus eventBus;
    /**
     * List of integer fields that are annotated as @NotNull in the model, used in a workaround for NPEs
     */
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
    @UiField
    NumberLabel<Integer> passiveWisdom;
    @UiField
    IntegerSpinner armorClass;
    @UiField
    NumberLabel<Integer> initiative;
    @UiField
    IntegerSpinner speed;
    @UiField
    IntegerSpinner maximumHitPoints;
    @UiField
    IntegerSpinner currentHitPoints;
    @UiField
    IntegerSpinner temporaryHitPoints;
    @UiField
    IntegerSpinner proficiency;
    @UiField
    CheckBox hasInspirationPoint;
    @UiField
    IntegerSpinner maximumHitDice;
    @UiField
    IntegerSpinner currentHitDice;

    @Inject
    public CharacterEditor(EventBus eventBus) {
        this.eventBus = eventBus;
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @UiHandler(value = {
            "name",
            "characterClass",
            "background",
            "race"
    })
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
            "charisma",
            "armorClass",
            "speed",
            "maximumHitPoints",
            "currentHitPoints",
            "temporaryHitPoints",
            "maximumHitPoints",
            "currentHitPoints",
            "temporaryHitPoints",
            "proficiency",
            "maximumHitDice",
            "currentHitDice"
    })
    void onIntegerValueChange(ValueChangeEvent<Integer> event) {
        /*
         If an Integer annotated @NotNull in the backing model is null here, an NPE will throw under certain circumstances
         (e.g. user enters text instead of an integer, or during a page reload). The correct behavior should be to
         indicate the invalid input to the user and not fire any events until the input is corrected.
          */
        if (event.getValue() != null) {
            fireCharacterEditEvent();
        }
    }

    @UiHandler(value = {
            "hasInspirationPoint"
    })
    void onBooleanValueChange(ValueChangeEvent<Boolean> event) {
        if (event.getValue() != null) {
            fireCharacterEditEvent();
        }
    }

    private void fireCharacterEditEvent() {
        eventBus.fireEvent(new CharacterEditEvent());
    }

    interface CharacterEditorUiBinder extends UiBinder<HTMLPanel, CharacterEditor> {
    }
}