package com.amoralesch.vdp.web.logic.fluent;

import com.amoralesch.vdp.web.logic.Field;
import com.amoralesch.vdp.web.logic.Rule;

public class RuleBuilder {
    private final Rule rule;

    public static RuleBuilder newRule(String verb, String path) {
        return new RuleBuilder(verb, path);
    }

    private RuleBuilder(String verb, String path) {
        rule = new Rule(verb, path);
    }

    public RuleBuilder forwardTo(String target) {
        rule.setForwardTo(target);

        return this;
    }

    public RuleBuilder withRequestField(Field field) {
        rule.addRequestField(field);

        return this;
    }

    public RuleBuilder withResponseField(Field field) {
        rule.addResponseField(field);

        return this;
    }

    public Rule build() {
        return rule;
    }
}
