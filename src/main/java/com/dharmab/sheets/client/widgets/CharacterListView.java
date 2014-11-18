package com.dharmab.sheets.client.widgets;

import com.dharmab.sheets.client.requestfactory.CharacterProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.CompositeEditor;
import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.web.bindery.event.shared.EventBus;

import java.util.ArrayList;
import java.util.List;


public class CharacterListView extends Composite implements CompositeEditor<List<CharacterProxy>, CharacterProxy, CharacterQuickView> {
    private static CharacterListViewUiBinder ourUiBinder = GWT.create(CharacterListViewUiBinder.class);
    EditorDelegate<List<CharacterProxy>> delegate;
    @UiField
    Panel panel;
    private EventBus eventBus;
    private CompositeEditor.EditorChain<CharacterProxy, CharacterQuickView> editorChain;
    private List<CharacterQuickView> subViews = new ArrayList<>();

    public CharacterListView(EventBus eventBus) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.eventBus = eventBus;
    }

    @Override
    public CharacterQuickView createEditorForTraversal() {
        return new CharacterQuickView(eventBus);
    }

    @Override
    public String getPathElement(CharacterQuickView subEditor) {
        return "[" + subViews.indexOf(subEditor) + "]";
    }

    @Override
    public void setEditorChain(EditorChain<CharacterProxy, CharacterQuickView> chain) {
        editorChain = chain;
    }

    @Override
    public void flush() {

    }

    @Override
    public void onPropertyChange(String... paths) {

    }

    @Override
    public void setValue(List<CharacterProxy> characters) {
        if (subViews == null) {
            subViews = new ArrayList<>();
        }
        for (CharacterQuickView subView : subViews) {
            editorChain.detach(subView);
        }
        subViews.clear();
        panel.clear();
        for (CharacterProxy character : characters) {
            CharacterQuickView subView = new CharacterQuickView(eventBus);
            panel.add(subView);
            editorChain.attach(character, subView);
        }
    }

    @Override
    public void setDelegate(EditorDelegate<List<CharacterProxy>> delegate) {
        this.delegate = delegate;
    }

    interface CharacterListViewUiBinder extends UiBinder<HTMLPanel, CharacterListView> {
    }
}