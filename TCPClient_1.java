import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient_1 {
    public void send(InetAddress address, int port) throws IOException {
        try (Socket socket = new Socket(address, port)) {
            System.out.println("Connected to server.");

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            InputStream in = socket.getInputStream();
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(in));

            OutputStream out = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out, true);

            String message;
            while (true) {
                System.out.print("Enter a message: ");
                message = userInput.readLine();

                writer.println(message);
                String response = serverReader.readLine();
                System.out.println(response);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        String serverAddress = args[0];
        int serverPort = Integer.parseInt(args[1]);

        TCPClient_1 client = new TCPClient_1();
        client.send(InetAddress.getByName(serverAddress), serverPort);
    }
}