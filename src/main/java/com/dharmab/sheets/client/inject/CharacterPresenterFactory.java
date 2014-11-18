package com.dharmab.sheets.client.inject;

import com.dharmab.sheets.client.places.CharacterPlace;
import com.dharmab.sheets.client.presenters.CharacterPresenter;

public interface CharacterPresenterFactory {
    CharacterPresenter create(CharacterPlace place);
}
