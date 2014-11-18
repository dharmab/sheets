package com.dharmab.sheets.client.views;

import com.dharmab.sheets.client.presenters.WelcomePresenter;
import com.dharmab.sheets.client.widgets.CharacterListView;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.user.client.ui.IsWidget;

public interface WelcomeView extends IsWidget, IsEditor<CharacterListView> {
    void setPresenter(WelcomePresenter presenter);
}
