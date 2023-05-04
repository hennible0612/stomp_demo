package stomp.scoketDemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // 메시지 브로커가 지원하는 WebSocket 메시지 처리
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {


    // 메시지 브로커 발신자와 수신자 사이의 중개자 역할을 한다.
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 가공해야할시
        registry.setApplicationDestinationPrefixes("/app"); // /app/chatroom /app/user/{유저이름}
        // queue 는 1 to 1, topic은 broadcast 할때 많이 사용
        registry.enableSimpleBroker("/chatroom", "/user", "/board");// 스프링 내장 브로커
        registry.setUserDestinationPrefix("/user"); // 특정 유저에게
    }

    // STOMP 클라이언트와 서버간의 연결을 설절, Stomp 엔드포인트 등록
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*").withSockJS(); //sorkJS사용 cors
    }



}
