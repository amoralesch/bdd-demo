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

<div>
<pre concordion:assert-equals="#response.body">{
  "0" : "",
  "1" : "hola",
  "2" : "mundo"
}
</pre>
</div>

### ~~Example~~

If the controller is used for a POST request, we can capture all the
fields in the body, and then do some validations on them.

Imagine that the request **requires** the first name to be present.

### [Example](- "post-multiple")

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
  "message" : "first name may not be null",
  "error" : "Internal Server Error",
  "status" : 500
}</pre></div>

### ~~Example~~
    
