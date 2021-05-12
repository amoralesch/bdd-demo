# Attributes

It is possible to have a client send a list of attributes, without
having to specify how many there are, or the name of each one of them.

### [Example](- "multiple")

When a client makes a _[POST](- "#method")_ **[/api/apply](- "#uri")**
HTTP request with the following body (formatted for readability):

<div>
<pre concordion:execute="#response=http(#method, #uri, #TEXT)">
  {
    "id" : "12345",
    "firstName" : "Steve",
    "lastName" : "Harris",
    "extraInfo" : {
      "one" : "uno",
      "two" : "dos",
      "three" : "tres"
    }
  }
</pre>
</div>

the application responds with [201](- "?=#response.status") status and
[application/json](- "?=#response.contentType") with the following
JSON in the body (formatted for readability):

<div><pre concordion:assert-equals="#response.body">{
  "FullName" : "Steve Harris",
  "Works?" : "Yes",
  "attributes" : "one:uno|two:dos|three:tres",
  "ID" : "12345"
}</pre></div>

### ~~Example~~

