import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public class HTTPResponse {

    private OutputStream outputStream;

    public HTTPResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void sendResponse(int statusCode, String statusMessage, String contentType, byte[] content)
            throws IOException {
        String response = "HTTP/1.0 " + statusCode + " " + statusMessage + "\r\n" +
                "Date: " + new Date() + "\r\n" +
                "Server: HTTPServer\r\n" +
                "Content-Length: " + content.length + "\r\n" +
                "Content-Type: " + contentType + "\r\n\r\n";
        outputStream.write(response.getBytes());
        outputStream.write(content);
        outputStream.flush();
    }

    public void sendError(int errorCode, String errorMessage) throws IOException {
        String response = "HTTP/1.0 " + errorCode + " " + errorMessage + "\r\n" +
                "Date: " + new Date() + "\r\n" +
                "Server: HTTPServer\r\n\r\n";
        outputStream.write(response.getBytes());
        outputStream.flush();
    }
}
