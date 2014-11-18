package com.dharmab.sheets.client.events;


import com.google.gwt.event.shared.GwtEvent;

public class CharacterSelectionEvent extends GwtEvent<CharacterSelectionEventHandler> {
    private static final Type<CharacterSelectionEventHandler> TYPE = new Type<>();

    int id;

    public CharacterSelectionEvent(int id) {
        this.id = id;
    }

    @Override
    public Type<CharacterSelectionEventHandler> getAssociatedType() {
        return TYPE;
    }

    public int getId() {
        return id;
    }

    @Override
    protected void dispatch(CharacterSelectionEventHandler handler) {
        handler.onCharacterSelection(this);
    }
}
