import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PutHandler implements HTTPHandler {

    @Override
    public void handle(HTTPRequest request, HTTPResponse response) throws IOException {
        String path = request.getPath().substring(1);

        StringBuilder requestBody = new StringBuilder();
        String line;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getClientSocket().getInputStream()))) {
            while ((line = reader.readLine()) != null) {
                requestBody.append(line).append("\r\n");
            }
        }

        response.sendResponse(200, "OK", "text/plain", requestBody.toString().getBytes());
    }
}
