package com.example.esercizio1.configs;

import com.example.esercizio1.websocket.AutoreWebSocketHandler;
import com.example.esercizio1.websocket.LibroWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final LibroWebSocketHandler libroWebSocketHandler;
    private final AutoreWebSocketHandler autoreWebSocketHandler;

    public WebSocketConfig(LibroWebSocketHandler libroWebSocketHandler, AutoreWebSocketHandler autoreWebSocketHandler) {
        this.libroWebSocketHandler = libroWebSocketHandler;
        this.autoreWebSocketHandler = autoreWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // Registra il handler per la URL "/ws/libro" che gestisce le connessioni WebSocket
        registry.addHandler(libroWebSocketHandler, "/ws/libro").setAllowedOrigins("*");
        registry.addHandler(autoreWebSocketHandler, "/ws/autore").setAllowedOrigins("*");
    }
}
