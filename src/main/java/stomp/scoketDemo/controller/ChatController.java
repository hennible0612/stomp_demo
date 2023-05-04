package stomp.scoketDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import stomp.scoketDemo.model.Message;


//https://platform.openai.com/docs/models/gpt-4
@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    // 메시지를 받은후 특정 주제에 보낸다.
    @MessageMapping("/message") // /app/message
    @SendTo("/chatroom/public")
    public Message receivePublicMessage(@Payload Message message) {
        return message;
    }

    @MessageMapping("/note")
    @SendTo("/board/public")
    public Message receiveNote(@Payload Message message){
        System.out.println(message.toString());
        return message;
    }

    @MessageMapping("/private-message")
    public Message reveiverPrivateMessage(@Payload Message message) {
        //prefix 자동으로 가져옴
        // user/{유저이름}/private
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message);
        System.out.println(message.toString());
        return message;
    }

}
