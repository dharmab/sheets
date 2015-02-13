package com.dharmab.sheets.server.character;

import com.dharmab.sheets.server.requestfactory.AbstractLocator;
import com.google.inject.Inject;
import org.hibernate.SessionFactory;

public class CharacterLocator extends AbstractLocator<Character, Integer, Integer> {
    @Inject
    public CharacterLocator(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Character create(Class<? extends Character> clazz) {
        Character character = new Character();
        character.initializeDefaults();
        return character;
    }

    @Override
    public Class<Character> getDomainType() {
        return Character.class;
    }

    @Override
    public Class<Integer> getIdType() {
        return Integer.class;
    }
}
