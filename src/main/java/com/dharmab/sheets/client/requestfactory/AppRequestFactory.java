package com.dharmab.sheets.client.requestfactory;

import com.google.web.bindery.requestfactory.shared.RequestFactory;

public interface AppRequestFactory extends RequestFactory {
    CharacterRequest getCharacterRequest();
}
