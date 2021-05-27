package com.amoralesch.vdp.web.logic;

public class Field {
    private final String name;

    private final String description;

    private final boolean required;

    private final int maxLen;

    private final String mapTo;

    private final String mustEqual;

    public Field(String name, String description, boolean required, int maxLen, String mapTo, String mustEqual) {
        this.name = name;
        this.description = description;
        this.required = required;
        this.maxLen = maxLen;
        this.mapTo = mapTo;
        this.mustEqual = mustEqual;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isRequired() {
        return required;
    }

    public int getMaxLen() {
        return maxLen;
    }

    public String getMapTo() {
        return mapTo;
    }

    public String getMustEqual() {
        return mustEqual;
    }
}
