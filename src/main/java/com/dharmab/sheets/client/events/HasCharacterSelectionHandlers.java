package com.dharmab.sheets.client.events;

import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

public interface HasCharacterSelectionHandlers extends HasHandlers {
    public HandlerRegistration addHasCharacterSelectionEventHandler(CharacterSelectionEventHandler handler);
}