package SimplyCRM.entities;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ChatRoom {
   private @Id
     @GeneratedValue Long id;
    private String name;
    private @ElementCollection List<Long> participants;
    private @ElementCollection List<Message> messages;
    public ChatRoom(String name, List<Long> participants){
        this.name = name;
        this.participants = participants;
        this.messages = new ArrayList<Message>();
    }
    public void addMessage(Message newM){
        messages.add(newM);
    }

}
