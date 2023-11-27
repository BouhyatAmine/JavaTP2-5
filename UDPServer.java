import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
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

    public void launch() {
        try {
            socket = new DatagramSocket(port);
            System.out.println("Ready to receive...");
            while (!socket.isClosed()) {
                receiveAndProcess();
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

    }
    private void receiveAndProcess() {
        try {
            DatagramPacket receivePacket = new DatagramPacket(receive_buf, BUFF_SIZE);
            socket.receive(receivePacket);
            byte[] receivedBytes = Arrays.copyOf(receivePacket.getData(), receivePacket.getLength());
            String receivedString = new String(receivedBytes, StandardCharsets.UTF_8);
            System.out.println("Received message: " + receivedString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "The UDP Server is listening in port" + port;
    }

    public static void main(String[] args) throws IOException {
        UDPServer myUDPserver = new UDPServer(Integer.parseInt(args[0]));
        System.out.println(myUDPserver);
        myUDPserver.launch();
    }
}
