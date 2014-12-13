package com.dharmab.sheets.client.views;

import com.dharmab.sheets.client.presenters.CharacterPresenter;
import com.dharmab.sheets.client.widgets.CharacterBasicsEditor;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.user.client.ui.IsWidget;

public interface CharacterView extends IsWidget, IsEditor<CharacterBasicsEditor> {
    void setPresenter(CharacterPresenter presenter);

    void showErrorMessage(String message);

    void hideErrorMessage();
}
