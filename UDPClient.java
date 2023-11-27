import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class UDPClient {
    public void send(InetAddress address, int port) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String input;
        System.out.print("Please enter your messages here: ");
        while ((input = console.readLine()) != null) {
            byte[] sendData = input.getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet = new DatagramPacket(sendData, sendData.length, address, port);
            socket.send(packet);
        }
    }
    public static void main(String[] args) throws IOException {
        String serverAddress = args[0];
        int serverPort = Integer.parseInt(args[1]);
        UDPClient client = new UDPClient();
        client.send(InetAddress.getByName(serverAddress), serverPort);
    }
}