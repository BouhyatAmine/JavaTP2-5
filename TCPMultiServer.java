import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TCPMultiServer {
    static final int DEFAULT_PORT = 8080;
    private int port;
    private List<ConnectionThread> clientThreads;

    public TCPMultiServer(int port) {
        this.port = port;
        this.clientThreads = new ArrayList<>();
    }

    public TCPMultiServer() {
        this(DEFAULT_PORT);
    }

    /**
     * Launches the TCP multiserver, setting up a ServerSocket to listen on the specified port.
     * Continuously accepts incoming client connections and creates a new ConnectionThread for each client.
     *
     * @throws RuntimeException if there is an error initializing the ServerSocket or handling a client connection.
     */
    public void launch() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running. Waiting for clients...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected from " + clientSocket.getInetAddress());
                ConnectionThread connectionThread = new ConnectionThread(clientSocket, this, clientSocket.getInetAddress().toString());
                clientThreads.add(connectionThread);
                connectionThread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Broadcasts a message to all connected clients.
     *
     * @param message the message to broadcast.
     */
    public void broadcast(String message) {
        for (ConnectionThread thread : clientThreads) {
            thread.sendMessage(message);
        }
    }

    /**
     * The main entry point for the TCPMultiServer application.
     * Creates and launches a TCP multiserver using the specified port number.
     *
     * @param args command-line arguments containing the server port.
     *             Usage: java TCPMultiServer <server_port>
     */
    public static void main(String[] args) {
        if (args.length > 1) {
            System.err.println("Usage: java TCPMultiServer <server_port>");
            System.exit(1);
        }

        try {
            int serverPort = Integer.parseInt(args[0]);
            TCPMultiServer multiServer = new TCPMultiServer(serverPort);
            multiServer.launch();
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid port number.");
            System.exit(1);
        }
    }
}

