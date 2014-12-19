package com.dharmab.sheets.client.events;

import com.google.gwt.event.shared.GwtEvent;


public class CharacterDeletionEvent extends GwtEvent<CharacterDeletionEventHandler> {
    public static Type<CharacterDeletionEventHandler> TYPE = new Type<>();

    int id;

    public CharacterDeletionEvent(int id) {
        this.id = id;
    }

    public Type<CharacterDeletionEventHandler> getAssociatedType() {
        return TYPE;
    }

    public int getId() {
        return id;
    }

    protected void dispatch(CharacterDeletionEventHandler handler) {
        handler.onCharacterDeletion(this);
    }
}
