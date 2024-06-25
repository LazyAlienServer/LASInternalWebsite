package com.las.lasbackenduser3000.websocket;

import org.java_websocket.client.WebSocketClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sunyinuo
 */
@Component
public class WebSocketClientConfigurer {

    private final String URL = "ws://server.lazyalienserver.top:3000/api/";

    @Bean
    public WebSocketClient webSocketClient() {
        try {
            Map<String,String> headers = new HashMap<>();
            headers.put("Authorization","@LazyAlienServer");
            MyWebSocketClient webSocketClient = new MyWebSocketClient(new URI(URL),headers);
            webSocketClient.connect();
            return webSocketClient;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

}

