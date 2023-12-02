import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient_1 {
    /**
     * Connects to a TCP server at the specified address and port, sending and receiving messages.
     *
     * @param address the InetAddress of the TCP server.
     * @param port    the port number of the TCP server.
     * @throws IOException if there is an error establishing a connection or sending/receiving messages.
     */
    public void send(InetAddress address, int port) throws IOException {
        try (Socket socket = new Socket(address, port);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             InputStream in = socket.getInputStream();
             BufferedReader serverReader = new BufferedReader(new InputStreamReader(in));
             OutputStream out = socket.getOutputStream();
             PrintWriter writer = new PrintWriter(out, true)) {

            startServerReaderThread(serverReader);

            String message;
            while (true) {
                System.out.print("Enter a message: ");
                message = userInput.readLine();
                writer.println(message);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Starts a separate thread to continuously read and print messages from the server.
     *
     * @param serverReader the BufferedReader for reading messages from the server.
     */
    private void startServerReaderThread(BufferedReader serverReader) {
        Thread serverReaderThread = new Thread(() -> {
            try {
                String response;
                while ((response = serverReader.readLine()) != null) {
                    System.out.println("\nServer: " + response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverReaderThread.start();
    }

    /**
     * The main entry point for the TCPClient_1 application.
     * Connects to a TCP server using the specified server address and port.
     *
     * @param args command-line arguments containing the server address and port.
     *             Usage: java TCPClient_1 <server_address> <server_port>
     * @throws IOException if there is an error establishing a connection or sending/receiving messages.
     */
    public static void main(String[] args) throws IOException {
        // Check if the correct number of command-line arguments is provided
        if (args.length != 2) {
            System.err.println("Usage: java TCPClient_1 <server_address> <server_port>");
            System.exit(1);
        }

        try {
            // Parse command-line arguments
            String serverAddress = args[0];
            int serverPort = Integer.parseInt(args[1]);

            // Create and launch the TCP client
            TCPClient_1 client = new TCPClient_1();
            client.send(InetAddress.getByName(serverAddress), serverPort);

        } catch (NumberFormatException e) {
            // Handle the case where the provided port number is not a valid integer
            System.err.println("Error: Invalid port number.");
            System.exit(1);
        }
    }
}


