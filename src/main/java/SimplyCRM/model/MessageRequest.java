package SimplyCRM.model;

import java.time.Instant;

import SimplyCRM.entities.Message;
import lombok.Getter;

@Getter
public class MessageRequest {
    private Long author;
    private String content;
    public MessageRequest(Long author, String content){
        this.author = author;
        this.content = content;
    }
    public Message toMessage(){
        return new Message(this.author, this.content, Instant.now().toEpochMilli());
    }
}
