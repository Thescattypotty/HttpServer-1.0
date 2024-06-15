import java.io.IOException;

public interface HTTPHandler {
    void handle(HTTPRequest request, HTTPResponse response) throws IOException;
}
