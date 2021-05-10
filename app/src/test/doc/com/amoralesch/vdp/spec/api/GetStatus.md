# Get Node Status Operation

In order to know if a node is up, any client (person or machine) can
ping the server to verify its status.

The entry point for this ping is the URL `/api/status`. If the server is
up and running, it will reply with a `Server Up` response.

### [Example](- "server-up c:status=Unimplemented")

When a client makes a _GET_ **/api/status**
HTTP request, the application
responds with 200 status and
application/json with the following 
JSON in the body (formatted for readability):

<div><pre>{
  "status" : "Server Up"
}</pre></div>

### ~~Example~~
