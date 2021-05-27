package com.amoralesch.vdp.web.logic.fluent;

import com.amoralesch.vdp.web.logic.Field;

public class FieldBuilder {
    private final Field field;

    public static FieldBuilder newField(String path) {
        return new FieldBuilder(path);
    }

    private FieldBuilder(String path) {
        field = new Field(path);
    }

    public FieldBuilder withDescription(String description) {
        field.setDescription(description);

        return this;
    }

    public FieldBuilder isRequired() {
        field.setRequired(true);

        return this;
    }

    public FieldBuilder withMaxLen(int maxLen) {
        field.setMaxLen(maxLen);

        return this;
    }

    public FieldBuilder mapTo(String mapTo) {
        field.setMapTo(mapTo);

        return this;
    }

    public FieldBuilder mustEqual(String mustEqual) {
        field.setMustEqual(mustEqual);

        return this;
    }

    public Field build() {
        return field;
    }
}
