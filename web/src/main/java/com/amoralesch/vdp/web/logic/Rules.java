package com.amoralesch.vdp.web.logic;

import java.util.ArrayList;
import java.util.List;

public abstract class Rules {
    private final List<Rule> rules = new ArrayList();

    public Rule getRule(String path, String verb) {
        return rules.stream()
            .filter(r -> r.getPath().equals(path) && r.getVerb().equals(verb))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("path " + verb + " " + path + " is not valid"));
    }

    protected void addRule(Rule r) {
        rules.add(r);
    }
}
