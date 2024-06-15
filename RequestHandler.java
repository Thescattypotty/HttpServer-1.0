import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class RequestHandler implements Runnable {

    private Socket clientSocket;
    private HTTPHandler fileServer;

    public RequestHandler(Socket clientSocket, HTTPHandler fileServer) {
        this.clientSocket = clientSocket;
        this.fileServer = fileServer;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                OutputStream out = clientSocket.getOutputStream()) {
            String request = in.readLine();
            if (request != null) {
                System.out.println("Request received: " + request);

                HTTPRequest httpRequest = new HTTPRequest(request, clientSocket);
                HTTPResponse httpResponse = new HTTPResponse(out);

                if (httpRequest.getMethod().equals("GET") && httpRequest.getPath().startsWith("/static/")) {
                    fileServer.handle(httpRequest, httpResponse);
                } else {
                    httpResponse.sendError(501, "Not Implemented");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
