# Very Dumb Project

## Description

This project was created sometime in 2021, while trying to do a demo for
a client about how to use [Concordion](https://concordion.org/)
internally to facilitate BDD.

As the intent of the project was to showcase the potential of the tools,
no time was spent on quality of the code, patterns, or any other good
practices that need to be followed when writing code that will be used
in any production environment.

If you like this code, please **do not use it in a production
environment**, the quality is lacking. If you need to implement something
similar, take this as an inspiration, but create the code following
TDD and BDD, the way code should be written.

## Branches

This project includes a number of branches, each one with a different.
intent:

* `master`: Basic project, it has Concordion running and a couple of
  examples to prove that you can create a mock WebAPI to simulate your
  application connecting to an external one, as well as accepting a
  dynamic number of attributes on the request body.
* `feature/demo`: Almost the same as master, with the last 3 commits
  created with the intent of showing how to train the people to do the
  work. Start with HEAD-2, explain how to create the specification; then
  HEAD-1, explain instrumentation; finally HEAD, and show how to do
  production code.
* `feature/dynamic-controller`: Continuing with the POC for the client,
  this branch shows how we can create a generic controller that accepts
  all possible calls, and then internally inspect the request and uses a
  set of rules (DSL) to validate the request and forward it to an external
  API. The _rules engine_ was designed to be controlled by a JSON object
  that could be passed as an external value.
* `feature/internal-dsl`: Same as `feature/dynamic-controller`, but using
  an internal DSL in java instead of JSON.
