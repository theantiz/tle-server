import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {

    public Consumer<Socket> getConsumer() {
        return (clientSocket) -> {
            try (PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true)) {
                toClient.println("Hello from the server");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public static void main(String[] args) {
        int port = 8020;
        Server server = new Server();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);
            while (true) {
                try {
                    Socket acceptedSocket = serverSocket.accept();
                    System.out
                            .println("Connection established with client: " + acceptedSocket.getRemoteSocketAddress());
                    Thread thread = new Thread(() -> server.getConsumer().accept(acceptedSocket));
                    thread.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
