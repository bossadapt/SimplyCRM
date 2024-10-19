package SimplyCRM.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Message {
    private @Id
     @GeneratedValue Long id;
    private Long author;
    private String content;
    private Long timeRecieved;
    public Message(Long author,String content,Long timeRecieved) {
        this.author = author;
        this.content = content;
        this.timeRecieved = timeRecieved;
      }
}
