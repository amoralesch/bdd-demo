# Data Validation

Anytime a request comes into the system, a verification should be made
to make sure that only valid info is provided. If any of the information
is garbage, the system should stop the process, and report an error to
the client.

For example, imagine that you have an entry point where you can do a 
`POST` operation, providing the fields `id`, `firstName` and `lastName`
in the body of the request. Of those, both `id` and `firstName` are
required, while `lastName` is optional. Let's look at examples of how
the system would react to missing properties.

### [Example](- "missing-id c:status=ExpectedToFail")

When a client makes a _[POST](- "#method")_ **[/api/apply](- "#uri")**
HTTP request with the following body (formatted for readability):

<div>
<pre concordion:execute="#response=http(#method, #uri, #TEXT)">
  {
    "firstName" : "Steve",
    "lastName" : "Harris"
  }
</pre>
</div>

the application responds with [400](- "?=#response.status") status and
[application/json](- "?=#response.contentType") with the following
JSON in the body (formatted for readability):

<div><pre concordion:assert-equals="encode(#response.body)">{
  "exception" : "HttpMessageNotReadableException",
  "message" : "JSON parse error: Cannot construct instance of `com.amoralesch.vdp.web.model.CustomRequest`, problem: ID may not be null; nested exception is com.fasterxml.jackson.databind.exc.ValueInstantiationException: Cannot construct instance of `com.amoralesch.vdp.web.model.CustomRequest`, problem: ID may not be null\n at [Source: (PushbackInputStream); line: 5, column: 3]",
  "error" : "Bad Request",
  "status" : 400
}</pre></div>

### ~~Example~~

### [Example](- "missing-firstName c:status=ExpectedToFail")

When a client makes a _[POST](- "#method")_ **[/api/apply](- "#uri")**
HTTP request with the following body (formatted for readability):

<div>
<pre concordion:execute="#response=http(#method, #uri, #TEXT)">
  {
    "id" : "123",
    "lastName" : "Harris"
  }
</pre>
</div>

the application responds with [400](- "?=#response.status") status and
[application/json](- "?=#response.contentType") with the following
JSON in the body (formatted for readability):

<div><pre concordion:assert-equals="encode(#response.body)">{
  "exception" : "HttpMessageNotReadableException",
  "message" : "JSON parse error: Cannot construct instance of `com.amoralesch.vdp.web.model.CustomRequest`, problem: first name may not be null; nested exception is com.fasterxml.jackson.databind.exc.ValueInstantiationException: Cannot construct instance of `com.amoralesch.vdp.web.model.CustomRequest`, problem: first name may not be null\n at [Source: (PushbackInputStream); line: 5, column: 3]",
  "error" : "Bad Request",
  "status" : 400
}</pre></div>

### ~~Example~~

As mentioned before, the field `lastName` is optional. As such, if the
request doesn't include it, the application will work normally and will
not return an error.

### [Example](- "missing-lastName c:status=ExpectedToFail")

When a client makes a _[POST](- "#method")_ **[/api/apply](- "#uri")**
HTTP request with the following body (formatted for readability):

<div>
<pre concordion:execute="#response=http(#method, #uri, #TEXT)">
  {
    "id" : "123",
    "firstName" : "Steve"
  }
</pre>
</div>

the application responds with [201](- "?=#response.status") status and
[application/json](- "?=#response.contentType") with the following
JSON in the body (formatted for readability):

<div><pre concordion:assert-equals="#response.body">{
  "FullName" : "Steve",
  "Works?" : "Yes",
  "ID" : "123"
}</pre></div>

### ~~Example~~
    
