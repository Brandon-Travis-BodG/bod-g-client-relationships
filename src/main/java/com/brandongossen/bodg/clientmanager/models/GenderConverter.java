package com.brandongossen.bodg.clientmanager.models;

import java.beans.PropertyEditorSupport;

public class GenderConverter extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(Gender.valueOf(text.toUpperCase()));
    }
}