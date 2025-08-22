import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public Runnable getRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                int port = 8020; // match server port
                try {
                    InetAddress address = InetAddress.getByName("localhost");
                    try (Socket socket = new Socket(address, port)) {
                        try (PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);
                                BufferedReader fromSocket = new BufferedReader(
                                        new InputStreamReader(socket.getInputStream()))) {

                            toSocket.println("Hello from Client " + socket.getLocalSocketAddress());
                            String line = fromSocket.readLine();
                            System.out.println("Response from the Server: " + line);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public static void main(String[] args) {
        Client client = new Client();
        for (int i = 0; i < 100; i++) {
            try {
                Thread thread = new Thread(client.getRunnable());
                thread.start(); // start thread properly
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
