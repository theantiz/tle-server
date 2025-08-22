
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {

    public void run() throws IOException {
        int port = 8010;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(10000);
        while (true) {

            try {
                System.out.println("Server is listening on Port" + port);
                Socket accpetedConnection = socket.accept();
                System.out.println("Connection accpeted by client" + accpetedConnection);
                PrintWriter toClient = new PrintWriter(accpetedConnection.getOutputStream()); // convert the data into
                                                                                              // bytes & then will send
                                                                                              // in Stream
                                                                                              // (OutputStream)
                BufferedReader fromClient = new BufferedReader(
                        new InputStreamReader(accpetedConnection.getInputStream())); // combine the byte from Stream &
                                                                                     // gives the result (InputStream)
                toClient.println("Hello from the Server");

                toClient.close();
                fromClient.close();
                accpetedConnection.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
