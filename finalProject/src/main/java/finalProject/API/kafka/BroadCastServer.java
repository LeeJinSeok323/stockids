package finalProject.API.kafka;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class BroadCastServer extends WebSocketServer {
    private static Set<WebSocket> connections = ConcurrentHashMap.newKeySet();
    public BroadCastServer(InetSocketAddress address) {
        super(address);
    }
    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        connections.add(conn);
        System.out.println("New WebSocket connection: " + conn.getRemoteSocketAddress());
    }
    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        connections.remove(conn);
        System.out.println("Closed WebSocket connection: " + conn.getRemoteSocketAddress());
    }
    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Received message from WebSocket client: " + message);
        // 여기에 WebSocket 클라이언트로부터 받은 메시지를 처리하는 로직을 추가할 수 있습니다.
    }
    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }
    @Override
    public void onStart() {
        System.out.println("WebSocket server started successfully");
    }
    public static void broadcastMessage(String message) {
        for (WebSocket conn : connections) {
            conn.send(message);
        }
    }
}
