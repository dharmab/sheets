package com.dharmab.sheets.client.events;

import com.google.gwt.event.shared.EventHandler;


public interface CharacterDeletionEventHandler extends EventHandler {
    void onCharacterDeletion(CharacterDeletionEvent event);
}
