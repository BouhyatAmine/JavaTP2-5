import java.io.*;
import java.net.*;

public class TCPServer {
    static int DEFAULT_PORT = 8080;
    protected int port;

    public TCPServer(int port) {
        this.port = port;
    }

    public TCPServer() {
        this(DEFAULT_PORT);
    }

    /**
     * Launches the TCP server, setting up a ServerSocket to listen on the specified port.
     * The server continuously accepts incoming client connections and delegates handling each connection
     * to the {@link #handleClientConnection(Socket)} method.
     *
     * @throws RuntimeException if there is an error initializing the ServerSocket or handling a client connection.
     */
    public void launch() {
        try (ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Server On. Waiting for client...");

            while (true) {
                handleClientConnection(serverSocket.accept());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles a client connection by setting up input and output streams, and continuously
     * reads messages from the client. Received messages are printed to the console, and a response
     * is sent back to the client.
     *
     * @param socket the Socket representing the client connection.
     * @throws IOException if there is an error setting up input/output streams or handling the client connection.
     */
    private void handleClientConnection(Socket socket) throws IOException {
        System.out.println("Client connected");

        try (InputStream in = socket.getInputStream();
             BufferedReader inputReader = new BufferedReader(new InputStreamReader(in));
             OutputStream out = socket.getOutputStream();
             PrintWriter writer = new PrintWriter(out, true)) {

            processClientMessages(socket, inputReader, writer);
        }
    }

    /**
     * Processes messages received from the client. Each received message is printed to the console,
     * and a response is sent back to the client.
     *
     * @param socket       the Socket representing the client connection.
     * @param inputReader  the BufferedReader for reading messages from the client.
     * @param writer       the PrintWriter for sending responses to the client.
     * @throws IOException if there is an error processing client messages or sending responses.
     */
    private void processClientMessages(Socket socket, BufferedReader inputReader, PrintWriter writer) throws IOException {
        String message;
        while (!socket.isClosed()) {
            message = inputReader.readLine();
            if (message != null) {
                System.out.println("Message received: " + message);
                sendResponse(writer, "Message sent back from the server: " + message);
            } else {
                System.out.println("Connection closed");
                socket.close();
            }
        }
    }

    /**
     * Sends a response message to the client using the provided PrintWriter.
     *
     * @param writer  the PrintWriter for sending responses to the client.
     * @param message the response message to be sent.
     */
    private void sendResponse(PrintWriter writer, String message) {
        writer.println(message);
    }

    /**
     * The main entry point for the TCPServer application.
     * Creates and launches a TCP server using the specified port number.
     *
     * @param args command-line arguments containing the server port.
     *             Usage: java TCPServer <server_port>
     * @throws IOException if there is an error initializing or launching the TCP server.
     */
    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.err.println("Usage: java TCPServer <server_port>");
            System.exit(1);
        }

        try {
            int serverPort = Integer.parseInt(args[0]);
            TCPServer myTCPserver = new TCPServer(serverPort);
            myTCPserver.launch();
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid port number.");
            System.exit(1);
        }
    }

}
