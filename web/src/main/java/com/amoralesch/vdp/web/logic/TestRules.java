package com.amoralesch.vdp.web.logic;

public class TestRules extends Rules {
    public TestRules() {
        super();

        Rule holaMundo = new Rule("/hola/mundo", "GET", "");
        Rule postTest = new Rule("/post/test", "POST", "/api/pco");

        Field f1 = new Field("id", "ID", false, 5, "requestId", null);
        Field f2 = new Field("user.firstName", "user's first name", true, 15, "userFirstName", null);
        Field f3 = new Field("user.lastName", "user's last name", false, 15, "user.lastName", null);

        Field e1 = new Field("external", "external", false, 0, "connected?", "yes");
        Field e2 = new Field("response.working", "working", true, 0, "response.value", null);

        postTest.addRequestField(f1);
        postTest.addRequestField(f2);
        postTest.addRequestField(f3);

        postTest.addResponseField(e1);
        postTest.addResponseField(e2);

        addRule(holaMundo);
        addRule(postTest);
    }
}
