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
   java UDPServer 8080

If no port is specified, the default port (8080) will be used.
You can modify the **BUFF_SIZE** and **DEFAULT_PORT** constants in the code according to your requirements.


# UDP Client

This Java program implements a simple UDP (User Datagram Protocol) client. It allows users to send messages to a UDP server.

## Usage

### Prerequisites

- Java Development Kit (JDK) installed on your machine.

### Running the Client

1. Open a terminal or command prompt.

2. Compile the Java program with the server address and port as command-line arguments:

   ```bash
   javac UDPClient.java 127.0.0.1 8080

3.The client will prompt you to enter messages. Type a message and press Enter to send it to the server:
   ```bash
   Please enter your messages here: Hello, UDP Server!
   ```
### Notes

The client runs in a loop, allowing you to continuously send messages to the server.
The client sends messages to the specified server address and port using UDP.

### Customisation

You can modify the code to change the server address and port as needed.
   
