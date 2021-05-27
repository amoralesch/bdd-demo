package com.amoralesch.vdp.web.logic;

public class Field {
    private final String name;

    private String description;

    private boolean required = false;

    private int maxLen = 0;

    private String mapTo;

    private String mustEqual;

    public Field(String path) {
        this.name = path;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description != null ? description : name;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isRequired() {
        return required;
    }

    public void setMaxLen(int maxLen) {
        this.maxLen = maxLen;
    }

    public int getMaxLen() {
        return maxLen;
    }

    public void setMapTo(String mapTo) {
        this.mapTo = mapTo;
    }

    public String getMapTo() {
        return mapTo != null ? mapTo : name;
    }

    public void setMustEqual(String mustEqual) {
        this.mustEqual = mustEqual;
    }

    public String getMustEqual() {
        return mustEqual;
    }
}
