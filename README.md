# exalt
Server pool project. 
The project handles requests and mimics the behavior of a server, the requests have the required size in the request url.
There are two possible scenarios:
1. Requested size is available -> server does a 10 seconds wait then return positive respose.
2. Requested size is not available -> server returns error message.

default port is 8080, size is passed as parameter in url.
Example: 
http://localhost:8080/storage?size=50
