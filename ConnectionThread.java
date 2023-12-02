import java.io.*;
import java.net.Socket;

public class ConnectionThread extends Thread {
    private final InputStream clientInputStream;
    private final PrintWriter clientWriter;
    private final TCPMultiServer server;
    private final String clientAddress;

    /**
     * Constructs a ConnectionThread with the specified client socket, server, and client address.
     *
     * @param clientSocket  the Socket representing the connected client.
     * @param server        the TCPMultiServer instance managing the connections.
     * @param clientAddress the IP address of the connected client.
     * @throws IOException if there is an error initializing input/output streams.
     */
    public ConnectionThread(Socket clientSocket, TCPMultiServer server, String clientAddress) throws IOException {
        this.clientInputStream = clientSocket.getInputStream();
        this.clientWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        this.server = server;
        this.clientAddress = clientAddress;
    }

    /**
     * Runs the thread, continuously reading messages from the connected client and broadcasting them to other clients.
     *
     * @throws RuntimeException if there is an error reading client messages or broadcasting messages.
     */
    @Override
    public void run() {
        try (BufferedReader inputReader = new BufferedReader(new InputStreamReader(clientInputStream))) {
            String message;
            while ((message = inputReader.readLine()) != null) {
                System.out.println("Client message from " + clientAddress + ": " + message);
                server.broadcast("Client message from " + getName() + " " +message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sends a message to the connected client.
     *
     * @param message the message to be sent.
     */
    public void sendMessage(String message) {
        clientWriter.println(message);
    }
}

