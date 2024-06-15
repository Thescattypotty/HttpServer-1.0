import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {

    private int port;
    private FileServer fileServer;

    public HTTPServer(int port, String rootDirectory) {
        this.port = port;
        this.fileServer = new FileServer(rootDirectory);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server listening on port " + port + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                Thread thread = new Thread(new RequestHandler(clientSocket, fileServer));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = 1202;
        String rootDirectory = "/path/to/your/static/files/directory";
        HTTPServer server = new HTTPServer(port, rootDirectory);
        server.start();
    }
}
