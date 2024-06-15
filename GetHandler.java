import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GetHandler implements HTTPHandler {

    @Override
    public void handle(HTTPRequest request, HTTPResponse response) throws IOException {
        String path = request.getPath().substring(1);

        if (path.equals("")) {
            path = "index.html";
        }

        Path filePath = Paths.get(path);
        if (Files.exists(filePath) && !Files.isDirectory(filePath)) {
            String contentType = guessContentType(filePath);

            byte[] content = Files.readAllBytes(filePath);

            response.sendResponse(200, "OK", contentType, content);
        } else {
            response.sendError(404, "Not Found");
        }
    }

    private String guessContentType(Path filePath) {
        String fileName = filePath.getFileName().toString();
        if (fileName.endsWith(".html") || fileName.endsWith(".htm")) {
            return "text/html";
        } else if (fileName.endsWith(".txt") || fileName.endsWith(".java") || fileName.endsWith(".csv")) {
            return "text/plain";
        } else if (fileName.endsWith(".gif")) {
            return "image/gif";
        } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (fileName.endsWith(".png")) {
            return "image/png";
        } else if (fileName.endsWith(".pdf")) {
            return "application/pdf";
        } else {
            return "application/octet-stream";
        }
    }
}
