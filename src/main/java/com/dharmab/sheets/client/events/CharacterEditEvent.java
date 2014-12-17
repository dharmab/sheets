package com.dharmab.sheets.client.events;

import com.google.gwt.event.shared.GwtEvent;


public class CharacterEditEvent extends GwtEvent<CharacterEditEventHandler> {
    public static Type<CharacterEditEventHandler> TYPE = new Type<>();

    public Type<CharacterEditEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(CharacterEditEventHandler handler) {
        handler.onCharacterEdit(this);
    }
}
