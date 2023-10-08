package http_server;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RestServer {
    private static final Log log = LogFactory.getLog(RestServer.class);

    public static void start() throws IOException {
        log.info("Http server starting...");
        HttpServerProvider provider = HttpServerProvider.provider();
        HttpServer server = provider.createHttpServer(new InetSocketAddress(HttpConfig.port), 100);
        server.createContext("/hello", new RestHandler());
        ExecutorService threadPool = Executors.newFixedThreadPool(HttpConfig.poolSize);
        server.setExecutor(threadPool);
        server.start();
        log.info("Http server started at port " + HttpConfig.port + "...");
    }

    public static void main(String[] args) {
        log.info("Starting server...");
        try {
            RestServer.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
