package com.dharmab.sheets.client;

import com.dharmab.sheets.client.inject.CharacterPresenterFactory;
import com.dharmab.sheets.client.inject.WelcomePresenterFactory;
import com.dharmab.sheets.client.places.CharacterPlace;
import com.dharmab.sheets.client.presenters.CharacterActivity;
import com.dharmab.sheets.client.presenters.WelcomeActivity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

public class AppActivityMapper implements ActivityMapper {
    private WelcomePresenterFactory welcomePresenterFactory;
    private CharacterPresenterFactory characterPresenterFactory;

    @Inject
    public AppActivityMapper(WelcomePresenterFactory welcomePresenterFactory, CharacterPresenterFactory characterPresenterFactory) {
        this.welcomePresenterFactory = welcomePresenterFactory;
        this.characterPresenterFactory = characterPresenterFactory;
    }

    @Override
    public Activity getActivity(Place place) {
        if (place instanceof CharacterPlace) {
            return (CharacterActivity) characterPresenterFactory.create((CharacterPlace) place);
        } else {
            return (WelcomeActivity) welcomePresenterFactory.create();
        }
    }
}
