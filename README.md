Initial source code from https://stackoverflow.com/questions/10069059/is-it-possible-to-run-a-socket-server-and-socket-client-on-the-same-machine

Current troubles:
~~- I need a Listener thread to remain active for new incoming requests, but currently the Listener is blocked by the active connection.~~ Multithread is working! Thank you Skrigak!!!
- I would prefer Listener be its own Runnable class to make stack traces more readable. 
- I/O requires a lot of try..catch blocks, which makes the code much harder to read. Because I still don't really understand I/O, I don't know how to consolidate things once I change them.

**Note to self:** Some of the code in the Listener.java and Connection.java files may be useful, and this can be found in the `listener-runnable` branch