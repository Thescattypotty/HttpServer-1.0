import java.io.IOException;

public class DeleteHandler implements HTTPHandler {

    @Override
    public void handle(HTTPRequest request, HTTPResponse response) throws IOException {
        String path = request.getPath().substring(1);

        String responseBody = "Deleted resource: " + path;

        response.sendResponse(200, "OK", "text/plain", responseBody.getBytes());
    }
}
