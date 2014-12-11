package com.dharmab.sheets.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IntegerBox;


public class IntegerSpinner extends Composite implements LeafValueEditor<Integer> {
    private static IntegerSpinnerUiBinder ourUiBinder = GWT.create(IntegerSpinnerUiBinder.class);

    @UiField
    IntegerBox field;

    public IntegerSpinner() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public Integer getValue() {
        return field.getValue();
    }

    @Override
    public void setValue(Integer value) {
        field.setValue(value);
    }

    @UiHandler("increment")
    void increment(@SuppressWarnings("UnusedParameters") ClickEvent event) {
        if (getValue() != null) {
            field.setValue(field.getValue() + 1);
        }
    }

    @UiHandler("decrement")
    void decrement(@SuppressWarnings("UnusedParameters") ClickEvent clickEvent) {
        if (getValue() != null) {
            field.setValue(field.getValue() - 1);
        }
    }

    interface IntegerSpinnerUiBinder extends UiBinder<HTMLPanel, IntegerSpinner> {
    }
}