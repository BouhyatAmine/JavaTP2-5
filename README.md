# JavaTP2-5
**Objective**: Develop a Client-Server Chat application, in UDP and TCP

# UDP Server

This Java program implements a basic UDP (User Datagram Protocol) server. It listens on a specified port for incoming UDP packets, receives them, and prints the content of the received messages.

## Features

- **UDP Server**: Listens for UDP packets on a specified port.
- **Continuous Reception**: The server continuously receives and processes UDP packets.
- **Prints Received Messages**: Prints the content of the received UDP messages.

## Usage

### Prerequisites

- Java Development Kit (JDK) installed on your machine.

### Running the Server

1. Open a terminal or command prompt.

2. Compile the Java program:
   ```bash
   javac UDPServer.java
3. Run the server with a specified port (e.g., 8080):
   ```bash
   java UDPServer 8080
   ```

If no port is specified, the default port (8080) will be used.
The server will start listening for UDP packets, and incoming messages will be printed to the console.
You can modify the **BUFF_SIZE** and **DEFAULT_PORT** constants in the code according to your requirements.


# UDP Client

This Java program implements a simple UDP (User Datagram Protocol) client. It allows users to send messages to a UDP server.

## Usage

### Prerequisites

- Java Development Kit (JDK) installed on your machine.

### Running the Client

1. Open a terminal or command prompt.

2. Compile the Java program:
   ```bash
   javac UDPClient.java
3. Run the client with the server address and port as command-line arguments:
   ```bash
   java UDPClient localhost 8080
   ```

4. The client will prompt you to enter messages. Type a message and press Enter to send it to the server:
   ```bash
   Please enter your messages here: Hello, UDP Server!
   ```
### Notes

The client runs in a loop, allowing you to continuously send messages to the server.
The client sends messages to the specified server address and port using UDP.

### Customisation

You can modify the code to change the server address and port as needed.
   
# TCP Server

This Java program implements a simple TCP (Transmission Control Protocol) server. The server listens for incoming client connections, receives messages from connected clients, prints the received messages, and sends a response back to the clients.

## Usage

### Prerequisites

- Java Development Kit (JDK) installed on your machine.

### Running the Server

1. Open a terminal or command prompt.

2. Compile the Java program:
   ```bash
   javac TCPServer.java
3. Run the server with a specified port (e.g., 8080):
   ```bash
   java TCPServer 8080
   ```

The server will start listening for client connections. Each connected client can send messages to the server, and the server will print the received messages.

To stop the server, manually terminate the program (e.g., press Ctrl + C).

**Customisation**

You can modify the code to change the server port as needed.

# TCP Client

This Java program implements a simple TCP (Transmission Control Protocol) client. The client connects to a TCP server, sends messages to the server, and continuously receives and prints responses from the server.

## Usage

### Prerequisites

- Java Development Kit (JDK) installed on your machine.

### Running the Client

1. Open a terminal or command prompt.

2. Compile the Java program:

   ```bash
   javac TCPClient_1.java
3. Run the client with the server address and port as command-line arguments:
   ```bash
   java TCPClient_1 localhost 8080
   ```
   
The client will connect to the server and prompt you to enter a message. Type a message and press Enter to send it to the server.

The client will continuously receive and print responses from the server.

To exit the client, manually terminate the program (e.g., press Ctrl + C).

**Notes**

The client runs in a loop, allowing you to continuously send messages to the server and receive responses.

The client uses a separate thread to read and print responses from the server, enabling concurrent communication.

**Customization**

You can modify the code to change the server address and port as needed.

# Overall Comments:
We weren't able to implement a chat bot due to a lack of time and skills; however the overall objective of the app is indeed accomplished and the Client-Server communication is well established in the interface.

In TCPClient_1, the code represents a basic interactive TCP client that sends user input to a server and continuously receives and prints messages from the server in a separate thread.

In TCPClient, 
