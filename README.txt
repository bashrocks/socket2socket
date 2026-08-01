Initial source code from https://stackoverflow.com/questions/10069059/is-it-possible-to-run-a-socket-server-and-socket-client-on-the-same-machine

Connection.java and Listener.java are not currently in use but remain
in case I need to add their functions back in.

Current troubles:
- I need a Listener thread to remain active for new incoming requests,
but currently the Listener is blocked by the active connection.
- I would prefer Listener be its own Runnable class to make stack
traces more readable. 
- I/O requires a lot of try..catch blocks, which makes the code much
harder to read. Because I still don't really understand I/O, I don't
know how to consolidate things once I change them.
