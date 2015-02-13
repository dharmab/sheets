package com.dharmab.sheets.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;


public class IntegerSpinner extends Composite implements LeafValueEditor<Integer>, HasValue<Integer>, HasValueChangeHandlers<Integer> {
    private static IntegerSpinnerUiBinder ourUiBinder = GWT.create(IntegerSpinnerUiBinder.class);

    @Inject
    EventBus eventBus;
    @UiField
    Button increment;
    @UiField
    IntegerBox field;
    @UiField
    Button decrement;

    public IntegerSpinner() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @UiHandler("field")
    void onValueChange(ValueChangeEvent<Integer> event) {
        resize();
        fireEvent(event);
    }

    @UiHandler("field")
    public void handleKeyPress(KeyPressEvent event) {
        resize();
    }

    /**
     * We have to handle key up events as well in order to handle backspace correctly.
     *
     * @param event
     */
    @UiHandler("field")
    public void handleKeyUp(KeyUpEvent event) {
        resize();
    }

    private void resize() {
        String text = String.valueOf(getValue());

        int numberOfDigits = text.equals("null") ? 2 : text.length();
        if (numberOfDigits < 2) {
            numberOfDigits = 2;
        }
        double width = 0.8 * numberOfDigits;
        field.setWidth(width + "em");
    }

    @Override
    public Integer getValue() {
        return field.getValue();
    }

    @Override
    public void setValue(Integer value) {
        field.setValue(value, false);
    }

    @Override
    public void setValue(Integer value, boolean fireEvents) {
        field.setValue(value, fireEvents);
        resize();
    }

    @UiHandler("increment")
    void increment(@SuppressWarnings("UnusedParameters") ClickEvent event) {
        addToValue(1);
    }

    @UiHandler("decrement")
    void decrement(@SuppressWarnings("UnusedParameters") ClickEvent clickEvent) {
        addToValue(-1);
    }

    private void addToValue(int addend) {
        if (field.getValue() != null) {
            setValue(field.getValue() + addend, true);
        }
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Integer> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

    interface IntegerSpinnerUiBinder extends UiBinder<HTMLPanel, IntegerSpinner> {
    }
}