package com.dharmab.sheets.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class CharacterPlace extends Place {
    private String characterId;

    public CharacterPlace(String characterId) {
        this.characterId = characterId;
    }

    public String getToken() {
        return characterId;
    }

    public static class Tokenizer implements PlaceTokenizer<CharacterPlace> {
        @Override
        public CharacterPlace getPlace(String token) {
            return new CharacterPlace(token);
        }

        @Override
        public String getToken(CharacterPlace place) {
            return place.getToken();
        }
    }
}
