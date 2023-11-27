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
            e.printStackTrace();
        }
    }

    public synchronized void broadcast(String message) {
        for (ConnectionThread thread : clientThreads) {
            thread.sendMessage(message);
        }
    }


    public static void main(String[] args) {
        TCPMultiServer multiServer = new TCPMultiServer();
        multiServer.launch();
    }
}
