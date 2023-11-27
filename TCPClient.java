import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient extends JFrame {
    private BufferedReader serverReader;
    private PrintWriter writer;
    private JTextField inputField;
    private JTextArea chatArea;
    private JButton sendButton;

    public TCPClient(InetAddress address, int port) throws IOException {
        super("Chat Client");

        Socket socket = new Socket(address, port);
        serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);

        setupUI();
        setupEventHandlers();

        receiveMessages();
    }

    private void setupUI() {
        chatArea = new JTextArea();
        chatArea.setEditable(false);

        inputField = new JTextField();
        JButton sendButton = new JButton("Send");

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        setLayout(new BorderLayout());
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        add(inputPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private void setupEventHandlers() {
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        sendButton = (JButton) getContentPane().getComponent(1);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String message = inputField.getText();
        writer.println(message);
        inputField.setText("");
    }

    private void receiveMessages() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String message;
                    while ((message = serverReader.readLine()) != null) {
                        appendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void appendMessage(String message) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                chatArea.append(message + "\n");
            }
        });
    }

    public static void main(String[] args) throws IOException {
        String serverAddress = args[0];
        int serverPort = Integer.parseInt(args[1]);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new TCPClient(InetAddress.getByName(serverAddress), serverPort);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
