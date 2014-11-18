package com.dharmab.sheets.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class WelcomePlace extends Place {
    public WelcomePlace() {
    }

    public static class Tokenizer implements PlaceTokenizer<WelcomePlace> {

        @Override
        public WelcomePlace getPlace(String token) {
            return new WelcomePlace();
        }

        @Override
        public String getToken(WelcomePlace place) {
            return null;
        }
    }
}
