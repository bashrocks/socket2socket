To do:
- [x] Accept client input for messages
- [x] Request client username
- [x] Validate usernames to avoid duplicates
- [X] Prepend username to each message
- [X] Echo messages from all clients to each client, not just to server
- [ ] Intentional disconnect from client that sends a terminate signal to server
- [ ] Server heartbeat messages and read timeout to close client thread

This program will run a very simple real-time chat. Many features are missing.

### 1. Running the server
Start `IRCServer.java` from the command line and pass the desired port as an argument. The server will open the port to listen for incoming connections. Example:
```java
~$ java IRCServer.java 60010
```

### 2. Running the client
To connect the server, you need to know its IP address and port. If both programs are running on the same computer, you only need the port.

Start `IRCClient.java` from the command line and pass the desired IP address and port as arguments. Example:
```java
~$ java IRCClient.java 8.8.8.8 60010
```
Or, for a localhost connection, only pass the port:
```java
~$ java IRCClient.java 60010
```

### 3. Setting the username
The program will ask the client for a username to be displayed alongside their messages. Usernames must be unique within the session. If the username is already in use by another user, the client will be prompted to enter something different. Follow the prompts to set your username.

### 4. Chat
Type messages and press Enter to send them. Your username will be prepended to let other users know who's saying what, and you will see other users' messages echoed onto your screen.

### 5. Disconnect
Formal disconnect features are not yet implemented. To quit the program, press Ctrl+C to terminate the process. Due to current limitations of the software, a user disconnecting will not automatically kill the thread on the server nor make the username available for reuse.