package com.las.lasbackenduser3000.websocket;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Map;

/**
 * @author sunyinuo
 */
@Slf4j
public class MyWebSocketClient extends WebSocketClient {

    public MyWebSocketClient(URI serverUri, Map<String,String> headers) {
        super(serverUri,headers);
    }


    @Override
    public void onOpen(ServerHandshake arg0) {
        log.info("------ MyWebSocketClient onOpen ------");
    }

    @Override
    public void onClose(int arg0, String arg1, boolean arg2) {
        log.info("------ WebSocket onClose ------{}",arg1);
    }


    @Override
    public void onError(Exception arg0) {
        log.error("------ WebSocket onError ------{}",arg0);
    }

    @Override
    public void onMessage(String response) {}


}

