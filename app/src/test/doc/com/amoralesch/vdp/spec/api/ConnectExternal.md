# How to work with another API

Sometimes, you need to your API to connect to another external API, for
those cases, it is possible to mock external APIs, and both check the
information that they receive, and the information that they return.

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

when a client makes a _[POST](- "#method")_ **[/api/ext-apply](- "#uri")**
HTTP request with the following body (formatted for readability):

<div><pre concordion:execute="#response=http(#method, #uri, #TEXT)">{
  "id" : "123",
  "firstName" : "Steve",
  "lastName" : "Harris"
}</pre></div>

the external API receives a request with the following body:

<div><pre concordion:assert-equals="getExternalRequest()">{
  "id" : "123",
  "fullName" : "Steve Harris"
}</pre></div>

and the application responds with [201](- "?=#response.status") status
and [application/json](- "?=#response.contentType") with the following 
JSON in the body (formatted for readability):

<div><pre concordion:assert-equals="#response.body">{
  "Response" : "{\n  \"external\" : \"yes\",\n  \"working\" : \"correct\"\n}",
  "Works?" : "Yes"
}</pre></div>

### ~~Example~~
