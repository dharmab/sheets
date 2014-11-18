package com.dharmab.sheets.server.character;

import com.google.inject.AbstractModule;

public class CharacterModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CharacterLocator.class);
    }
}
