Initial source code from https://stackoverflow.com/questions/10069059/is-it-possible-to-run-a-socket-server-and-socket-client-on-the-same-machine

Current troubles:
- ~~I need a Listener thread to remain active for new incoming requests, but currently the Listener is blocked by the active connection.~~ Multithread is working! Thank you Skrigak!!!
- ~~I would prefer Listener be its own Runnable class to make stack traces more readable.~~ Unfortunately I don't think this is doable in this implementation & time frame.
- ~~I/O requires a lot of try..catch blocks, which makes the code much harder to read. Because I still don't really understand I/O, I don't know how to consolidate things once I change them.~~ Getting better at seeing this and catching it.
- validateProtocol seems to be working on the server side if it's not enabled on the client side; typed client input at sendMessage is interpreted as a protocol and throws an error on the server side. 

To do:
- [x] Accept client input for messages
- [ ] Assign client ID numbers or usernames - or request? (validate to avoid duplicates?)
- [ ] Prepend ID to each message
- [ ] Echo messages from all clients to each client, not just to server
- [ ] Close client thread when they disconnect (check .isInputShutdown ?)

**Note to self:** Some of the code in the Listener.java and Connection.java files may be useful, and this can be found in the `listener-runnable` branch