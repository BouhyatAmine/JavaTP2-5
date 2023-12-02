import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class UDPServer {
    static int BUFF_SIZE = 1024;
    static int DEFAULT_PORT = 8080;
    private DatagramSocket socket;
    protected int port;
    protected byte[] receive_buf = new byte[BUFF_SIZE];

    public UDPServer(int port) {
        this.port = port;
    }

    public UDPServer() {
        this(DEFAULT_PORT);
    }

    /**
     * Launches the UDP server, setting up a DatagramSocket to listen on the specified port.
     * The server continuously receives incoming datagrams and processes them.
     *
     * @throws RuntimeException if there is an error initializing the DatagramSocket.
     */
    public void launch() {
        try {
            socket = new DatagramSocket(port);
            System.out.println("Ready to receive...");
            while (!socket.isClosed()) {
                receiveAndDisplay();
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Receives a datagram packet on the associated DatagramSocket, processes the received data,
     * and prints the received message to the console.
     *
     * @throws RuntimeException if there is an error receiving or processing the datagram packet.
     */
    private void receiveAndDisplay() {
        DatagramPacket receivePacket = new DatagramPacket(receive_buf, BUFF_SIZE);
        try {
            socket.receive(receivePacket);
            byte[] receivedBytes = Arrays.copyOf(receivePacket.getData(), receivePacket.getLength());
            String receivedString = new String(receivedBytes, StandardCharsets.UTF_8);
            System.out.println("Received message: " + receivedString);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "The UDP Server is listening in port" + port;
    }

    /**
     * Launches a UDP server using the provided port number.
     *
     * @param args command-line arguments containing the server port.
     *             Usage: java UDPServer <server_port>
     */
    public static void main(String[] args) {
        if (args.length > 1) {
            System.err.println("Usage: java UDPServer <server_port>");
            System.exit(1);
        }

        try {
            int serverPort = Integer.parseInt(args[0]);
            UDPServer myUDPserver = new UDPServer(serverPort);
            System.out.println(myUDPserver);
            myUDPserver.launch();
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid port number.");
            System.exit(1);
        }
    }

}
