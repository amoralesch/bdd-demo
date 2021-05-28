package com.amoralesch.vdp.web.logic;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.function.Function;

public class Field {
    private final String name;

    private String description = null;

    private boolean required = false;

    private boolean shouldBePresent = true;

    private int maxLen = 0;

    private String mapTo = null;

    private String mustEqual = null;

    private Function<JsonNode, String> errorMessageGenerator = null;

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

    public void setShouldBePresent(boolean shouldBePresent) {
        this.shouldBePresent = shouldBePresent;
    }

    public boolean getShouldBePresent() {
        return shouldBePresent;
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

    public void setErrorMessageGenerator(Function<JsonNode, String> function) {
        errorMessageGenerator = function;
    }

    public Function<JsonNode, String> getErrorMessageGenerator() {
        return errorMessageGenerator;
    }
}
