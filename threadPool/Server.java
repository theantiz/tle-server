import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {

    private final ExecutorService threadPool;
    private final AtomicInteger clientCount = new AtomicInteger(0);

    public Server(int poolSize) {
        this.threadPool = Executors.newFixedThreadPool(poolSize);
    }

    public void handleClient(Socket clientSocket) {
        int count = clientCount.incrementAndGet();
        System.out.println("Client connected: IP = " + clientSocket.getInetAddress().getHostAddress()
                + ", Port = " + clientSocket.getPort()
                + ". Total clients: " + count);
        try (PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true)) {
            toClient.println("Hello from the server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = 8020;
        int poolSize = 10;
        Server server = new Server(poolSize);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setSoTimeout(80000);
            System.out.println("Server is listening on port " + port);
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    server.threadPool.execute(() -> server.handleClient(clientSocket));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            server.threadPool.shutdown();
        }
    }
}
