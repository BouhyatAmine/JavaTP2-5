import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class UDPClient {
    /**
     * Establishes a connection to a UDP server at the specified address and port,
     * and sends messages entered by the user to the server.
     *
     * @param address the InetAddress of the UDP server.
     * @param port    the port number of the UDP server.
     * @throws IOException if there is an error establishing a connection or sending messages.
     */
    public void connectAndSend(InetAddress address, int port) throws IOException {
        try {
            DatagramSocket socket = new DatagramSocket();
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            send(address, port, console, socket);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Sends messages entered by the user to the specified UDP server address and port.
     *
     * @param address the InetAddress of the UDP server.
     * @param port    the port number of the UDP server.
     * @param console a BufferedReader for reading user input from the console.
     * @param socket  the DatagramSocket used for communication.
     * @throws IOException if there is an error sending messages.
     */
    private static void send(InetAddress address, int port, BufferedReader console, DatagramSocket socket) throws IOException {
        String input;
        while ((input = console.readLine()) != null) {
            System.out.print("Please enter your messages here: ");
            byte[] sendData = input.getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet = new DatagramPacket(sendData, sendData.length, address, port);
            socket.send(packet);
        }
    }

    public static void main(String[] args) throws IOException {
        String serverAddress = args[0];
        int serverPort = Integer.parseInt(args[1]);
        UDPClient client = new UDPClient();
        client.connectAndSend(InetAddress.getByName(serverAddress), serverPort);
    }
}