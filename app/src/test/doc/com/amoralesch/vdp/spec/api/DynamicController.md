# Dynamic controllers

It is possible to create a generic controller that will take of receiving
all requests sent to the server, and then, dynamically, decide how to 
process them.

### [Example](- "dynamic")

When a client makes a _[GET](- "#method")_ **[/api/dyn/hola/mundo](- "#uri")**
[HTTP request](- "#response=http(#method, #uri)"), the application
responds with [200](- "?=#response.status") status and
[application/json](- "?=#response.contentType") with  the following 
JSON in the body (formatted for readability):

<div><pre concordion:assert-equals="#response.body">{
  "0" : "",
  "1" : "hola",
  "2" : "mundo"
}</pre></div>

### ~~Example~~

Obviously, if the mapping doesn't exist, the server replies with an 
exception.

### [Example](- "missing-get")

When a client makes a _[GET](- "#method")_ **[/api/dyn/no/existo](- "#uri")**
[HTTP request](- "#response=http(#method, #uri)"), the application
responds with [500](- "?=#response.status") status and
[application/json](- "?=#response.contentType") with  the following
JSON in the body (formatted for readability):

<div><pre concordion:assert-equals="encode(#response.body)">{
  "exception" : "IllegalArgumentException",
  "message" : "path GET /no/existo is not valid",
  "error" : "Internal Server Error",
  "status" : 500
}</pre></div>

### ~~Example~~

If the controller is used for a POST request, we can capture all the
fields in the body, and then do some validations on them.

Imagine that the request **requires** the first name to be present.

### [Example](- "post-missing")

When a client makes a _[POST](- "#method")_ **[/api/dyn/post/test](- "#uri")**
HTTP request with the following body (formatted for readability):

<div>
<pre concordion:execute="#response=http(#method, #uri, #TEXT)">
  {
    "id" : "123",
    "user" : {
      "lastName" : "Harris",
      "job" : "bassist"
    }
  }
</pre>
</div>

the application responds with [500](- "?=#response.status") status and
[application/json](- "?=#response.contentType") with the following
JSON in the body (formatted for readability):

<div><pre concordion:assert-equals="encode(#response.body)">{
  "exception" : "IllegalArgumentException",
  "message" : "user's first name may not be null",
  "error" : "Internal Server Error",
  "status" : 500
}</pre></div>

### ~~Example~~
    
Similarly, we can check for max length.

### [Example](- "post-len")

When a client makes a _[POST](- "#method")_ **[/api/dyn/post/test](- "#uri")**
HTTP request with the following body (formatted for readability):

<div>
<pre concordion:execute="#response=http(#method, #uri, #TEXT)">
  {
    "id" : "123",
    "user" : {
      "firstName" : "Long John The Magical Steve",
      "lastName" : "Harris",
      "job" : "bassist"
    }
  }
</pre>
</div>

the application responds with [500](- "?=#response.status") status and
[application/json](- "?=#response.contentType") with the following
JSON in the body (formatted for readability):

<div><pre concordion:assert-equals="encode(#response.body)">{
  "exception" : "IllegalArgumentException",
  "message" : "user's first name may not be longer than 15 characters",
  "error" : "Internal Server Error",
  "status" : 500
}</pre></div>

### ~~Example~~
    
If the information is correct, then the aplication will forward the
request as expected.

### [Example](- "external")

Given that:

* there is an external API that is listening to the end-point
  `/api/pco`;
* the external API always responds with the following JSON information
  (formatted for readability):

<div><pre concordion:execute="setFakeResponse(#TEXT)">{
  "external" : "yes",
  "working" : "correct"
}</pre></div>

when a client makes a _[POST](- "#method")_ **[/api/dyn/post/test](- "#uri")**
HTTP request with the following body (formatted for readability):

<div><pre concordion:execute="#response=http(#method, #uri, #TEXT)">{
  "id" : "123",
  "user" : {
    "firstName" : "Steve",
    "lastName" : "Harris",
    "job" : "bassist"
  }
}</pre></div>

the external API receives a request with the following body:

<div><pre concordion:assert-equals="getExternalRequest()">{
  "requestId" : "123",
  "userFirstName" : "Steve",
  "user" : {
    "lastName" : "Harris"
  }
}</pre></div>

and the application responds with [201](- "?=#response.status") status
and [application/json](- "?=#response.contentType") with the following
JSON in the body (formatted for readability):

<div><pre concordion:assert-equals="#response.body">{
  "external" : "yes",
  "working" : "correct"
}</pre></div>

### ~~Example~~

