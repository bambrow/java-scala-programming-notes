package http_server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RestHandler implements HttpHandler {
    private static final Log log = LogFactory.getLog(RestHandler.class);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        if (requestMethod.equalsIgnoreCase("GET")) {
            // set response headers
            Headers responseHeaders = exchange.getResponseHeaders();
            responseHeaders.set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, 0);

            // get query from uri
            String query = exchange.getRequestURI().getQuery();
            Map<String, String> queryMap = formData2Dic(query);
            String user = queryMap.getOrDefault("user", "bambrow");

            // send response
            OutputStream response = exchange.getResponseBody();
            String content = "Hello, " + user;
            response.write(content.getBytes(StandardCharsets.UTF_8));
            response.close();
        }
    }

    public static Map<String,String> formData2Dic(String formData) {
        Map<String,String> result = new HashMap<>();
        if (formData== null || formData.trim().length() == 0) {
            return result;
        }
        final String[] items = formData.split("&");
        Arrays.stream(items).forEach(item -> {
            final String[] keyAndVal = item.split("=");
            if (keyAndVal.length == 2) {
                try {
                    final String key = URLDecoder.decode( keyAndVal[0],"utf8");
                    final String val = URLDecoder.decode( keyAndVal[1],"utf8");
                    result.put(key,val);
                } catch (UnsupportedEncodingException ignored) {
                }
            }
        });
        return result;
    }
}
