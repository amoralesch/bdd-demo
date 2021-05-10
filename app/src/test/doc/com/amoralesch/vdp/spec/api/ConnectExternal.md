# How to work with another API

Sometimes, you need to your API to connect to another external API, for
those cases, it is possible to mock external APIs, and both check the
information that they receive, and the information that they return.

### [Example](- "external c:status=Unimplemented")

Given that:

* there is an external API that is listening to the end-point 
  `/api/pco`;
* the external API always responds with the following JSON information
  (formatted for readability):

<div><pre>{
  "external" : "yes",
  "working" : "correct"
}</pre></div>

when a client makes a _POST_ **/api/ext-apply**
HTTP request with the following body (formatted for readability):

<div><pre>{
  "id" : "123",
  "firstName" : "Steve",
  "lastName" : "Harris"
}</pre></div>

the external API receives a request with the following body:

<div><pre>{
  "id" : "123",
  "fullName" : "Steve Harris"
}</pre></div>

and the application responds with 201 status
and application/json with the following 
JSON in the body (formatted for readability):

<div><pre>{
  "Response" : "{\n  \"external\" : \"yes\",\n  \"working\" : \"correct\"\n}",
  "Works?" : "Yes"
}</pre></div>

### ~~Example~~

It is also possible to test what the application will behave when the
external API is down, or a time-out occurred

### [Example](- "timeout c:status=Unimplemented")

Given that the external API is down and not responding 
to requests, when a client makes a _POST_ 
**/api/ext-apply** HTTP request with the following body 
(formatted for readability):

<div><pre>{
  "id" : "123",
  "firstName" : "Steve",
  "lastName" : "Harris"
}</pre></div>

the application responds with 500 status
and application/json with the following
JSON in the body (formatted for readability):

<div><pre>{
  "exception" : "ReactiveException",
  "message" : "java.util.concurrent.TimeoutException: Did not observe any item or terminal signal within 3000ms in 'flatMap' (and no fallback has been configured)",
  "error" : "Internal Server Error",
  "status" : 500
}</pre></div>

### ~~Example~~
