package com.dharmab.sheets.client.views;

import com.dharmab.sheets.client.presenters.CharacterPresenter;
import com.google.gwt.user.client.ui.IsWidget;

public interface CharacterView extends IsWidget {
    void setPresenter(CharacterPresenter presenter);
}
