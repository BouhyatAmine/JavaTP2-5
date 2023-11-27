import java.io.*;
import java.net.Socket;

public class ConnectionThread extends Thread {
    private final InputStream clientInputStream;
    private final PrintWriter clientWriter;
    private final TCPMultiServer server;
    private final String clientAddress;

    public ConnectionThread(Socket clientSocket, TCPMultiServer server, String clientAddress) throws IOException {
        this.clientInputStream = clientSocket.getInputStream();
        this.clientWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        this.server = server;
        this.clientAddress = clientAddress;
    }

    @Override
    public void run() {
        try (BufferedReader inputReader = new BufferedReader(new InputStreamReader(clientInputStream))) {
            String message;
            while ((message = inputReader.readLine()) != null) {
                System.out.println("Client message from " + clientAddress + ": " + message);
                server.broadcast(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        clientWriter.println(message);
    }
}
