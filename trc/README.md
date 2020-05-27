this is a simple HTTP Denial-of-Service protection system.
Client

The user inputs the number of HTTP clients to simulate.

For each HTTP client you will run a separate concurrent thread/stask, which will do the following in a loop:

· Send HTTP request to a server with simulated HTTP client identifier as a query parameter (e.g.http://localhost:8080/?clientId=3).

· The client identifiers are random integers between 1 to the number of clients. Different clients can have the same identifier.

· Wait some random time and then send another request.

· The client will run until key press after which it will gracefully drain all the threads/tasks (wait for all of them to complete) and will exit.

---- in the Client I used CountDownLatch class : A synchronization aid that allows one or more threads to wait until a set of operations being performed in other threads completes.
in this way i can control the threads that are running concurrently.

Server

Starts listening for incoming HTTP requests.

For each incoming HTTP request, you will do the following:

· Handle the request in a separate thread/task.

· Check if this specific client reached the max number of requests per time frame threshold (no more than 5 requests per 5 secs).

· If the client hasn’t reached the threshold, it will get HTTP response with status code 200 (OK) otherwise status code 503 (Service Unavailable).

· Note: The time frame starts on each client’s first request and ends 5 seconds later.

· After the time frame has ended, the client’s first request will open a new time frame and so forth.

· The server will run until key press after which it will gracefully drain all the threads/tasks and will exit.

----the Server side has an entity which is the Client. the Client's controller handles the post and get requests from client.
I've used ThreadPoolExecutor to create and confige Server side. 