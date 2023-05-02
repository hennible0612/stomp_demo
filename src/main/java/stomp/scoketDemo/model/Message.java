package stomp.scoketDemo.model;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Message {
    private String senderName;
    private String receiverName;
    private String message;
    private String date;
    private Status status;


}
