package com.incra.models.propertyEditor;

import com.incra.models.Season;
import com.incra.services.SeasonService;

import java.beans.PropertyEditorSupport;

public class SeasonPropertyEditor extends PropertyEditorSupport {
    private final SeasonService seasonService;

    public SeasonPropertyEditor(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(seasonService.findEntityById(Integer.parseInt(text)));
    }

    @Override
    public String getAsText() {
        Object value = getValue();

        if (value instanceof Season) {
            return String.valueOf(((Season) value).getId());
        } else {
            return super.getAsText();
        }
    }
}
