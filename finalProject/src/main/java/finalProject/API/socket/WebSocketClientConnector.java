package finalProject.API.socket;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class WebSocketClientConnector {
    private WebSocketSession clientSession;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    @Value("${websocket.server.url}")
    private String serverUrl;  // application.properties에서 설정

    @PostConstruct
    public void init() {
        connect();
        scheduleReconnection();
    }

    public void connect() {
        if (isConnected()) {
            return;
        }

        WebSocketClient client = new StandardWebSocketClient();
        WebSocketHandler handler = new KafkaWebSocketHandler();

        try {
            clientSession = client.execute(handler, serverUrl).get(5, TimeUnit.SECONDS);
            log.info("WebSocket 연결 성공: {}", clientSession.getId());
        } catch (Exception e) {
            log.error("WebSocket 연결 실패: {}", e.getMessage());
        }
    }

    private void scheduleReconnection() {
        scheduler.scheduleWithFixedDelay(() -> {
            if (!isConnected()) {
                log.info("재연결 시도 중...");
                connect();
            }
        }, 0, 5, TimeUnit.SECONDS);  // 5초마다 연결 상태 체크
    }

    public boolean isConnected() {
        return clientSession != null && clientSession.isOpen();
    }

    @PreDestroy
    public void cleanup() {
        if (clientSession != null && clientSession.isOpen()) {
            try {
                clientSession.close();
            } catch (IOException e) {
                log.error("WebSocket 종료 중 에러: {}", e.getMessage());
            }
        }
        scheduler.shutdown();
    }
}
