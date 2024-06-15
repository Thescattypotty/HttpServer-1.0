import java.net.Socket;

public class HTTPRequest {

    private String method;
    private String path;
    private String httpVersion;
    private Socket clientSocket; 

    public HTTPRequest(String requestLine, Socket clientSocket) {
        this.clientSocket = clientSocket; 
        String[] parts = requestLine.split(" ");
        if (parts.length >= 3) {
            this.method = parts[0];
            this.path = parts[1];
            this.httpVersion = parts[2];
        } else {
            throw new IllegalArgumentException("Invalid HTTP request line: " + requestLine);
        }
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }
}
