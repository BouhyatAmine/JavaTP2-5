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

    public void launch() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server On. Waiting for client...");

            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                InputStream in = socket.getInputStream();
                BufferedReader inputReader = new BufferedReader(new InputStreamReader(in));

                OutputStream out = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(out, true);

                String message = "";

                while (!socket.isClosed()) {
                    message = inputReader.readLine();
                    if (message != null){
                        System.out.println("Message  received: " + message);
                        writer.println("Message sent back from the server: " + message);
                    } else {
                        System.out.println("Connection closed");
                        socket.close();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        TCPServer myTCPserver = new TCPServer(Integer.parseInt(args[0]));
        myTCPserver.launch();
    }
}
