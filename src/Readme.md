# 🌐 Java Multi-Threaded Echo Socket Application

This project provides a complete example for teaching Java Sockets, featuring a client-server architecture where the server uses **multi-threading** to handle multiple clients concurrently.

The application is structured using a Java **package** named `echoserver`, which requires specific steps for compilation and execution.

---

## 📂 Project Files and Structure

The project consists of four Java files designed to reside in a specific directory structure that matches the `echoserver` package:

| File Name | Description |
| :--- | :--- |
| **`MultiThreadedServer.java`** | Contains the main loop that accepts new client connections and delegates each one to a new thread. |
| **`ClientHandler.java`** | Implements the `Runnable` interface. This is the logic run by each thread to handle communication with a single client (echos messages). |
| **`EchoClient.java`** | The client application used to connect to the server, send messages, and receive echoes. |
| **`EchoServer.java`** | (Optional, for Part 1) The original single-client sequential server. |

### Required Directory Structure

If your Java files contain the line `package echoserver;`, your filesystem must be structured as follows:

/socket-project-root/ 
|-- echoserver/ | 
|-- MultiThreadedServer.java | 
|-- ClientHandler.java | 
|-- EchoClient.java | 
|-- EchoServer.java

**Note:** All commands below should be run from the **Project Root** directory (the directory that contains the `echoserver` folder).

---

## 🛠️ Compilation Instructions

To compile packaged Java code, we use the `-d .` flag to tell the compiler (`javac`) to place the compiled `.class` files into the current directory (`.`), preserving the package structure.

### 1. Navigate to the Root Directory

```bash
cd /path/to/socket-project-root

2. Compile All Source Files
This command compiles all four files and places the resulting .class files into the newly created (or existing) echoserver/ folder.

Bash

# From /socket-project-root/:
javac echoserver/*.java -d .
🚀 Execution Instructions
Since all classes belong to the echoserver package, you must use their fully qualified name (packagename.ClassName) to run the applications.

1. Start the Server (Daemon)
Start the MultiThreadedServer, providing the desired listening port (e.g., 5000) as the command-line argument.

Bash

# Format: java [package.ClassName] [port]
java echoserver.MultiThreadedServer 5000
Server Output:

Multi-threaded Server listening on port 5000...
Ready to accept a new connection...
2. Connect the Client(s)
Open one or more new terminal windows. Run the EchoClient, providing the server's hostname/IP and the port number as arguments.

Bash

# Format: java [package.ClassName] [host] [port]
java echoserver.EchoClient localhost 5000
Client Output:

Connected to server localhost:5000
Enter text to send (type 'bye' to disconnect):
3. Test Concurrency
Run multiple instances of the client. When a client sends the message bye, only that specific connection closes, and the server remains running, ready for the next client.