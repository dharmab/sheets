package com.dharmab.sheets.client.validation;

import com.google.gwt.validation.client.GwtValidation;

import javax.validation.Validator;

@GwtValidation(com.dharmab.sheets.shared.character.Character.class)
public interface AppValidator extends Validator {
}
