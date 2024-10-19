package SimplyCRM.controller;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import SimplyCRM.entities.ChatRoom;
import SimplyCRM.entities.Message;
import SimplyCRM.model.BasicResponse;
import SimplyCRM.model.MessageRequest;
import SimplyCRM.model.NewChatRequest;
import SimplyCRM.repository.ChatRoomRepository;
import SimplyCRM.service.ChatService;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@EntityScan("SimplyCRM.entities*")  
public class ChatRoomController {
    private final ChatRoomRepository repository;
    private final ChatService chatService;
  
  @PostMapping("/chatroom")
  BasicResponse newChatRoom(@RequestBody NewChatRequest request) {
    return chatService.createChatRoom(request.getName(),request.getParticipants());
  }
  @MessageMapping("/chat/{id}.sendMessage")
  @SendTo("/chat/{id}")
  public Message sendMessage(@PathVariable Long id, MessageRequest messageRequest){
    Message message = messageRequest.toMessage();
    repository.findById(id).map(chatRoom -> {
      chatRoom.addMessage(message);
      return repository.save(chatRoom);
    }).orElseThrow(() -> new RuntimeException("unable to find chatroom ID#"+id));
    return message;
  }
   @GetMapping("/chatroom/{id}")
  ChatRoom chatroomByID(@PathVariable Long id) {
    return repository.findById(id)
      .orElseThrow(() -> new RuntimeException("unable to find chatroom ID#"+id));
  }
  @DeleteMapping("/chatroom/{id}")
  void deleteChatroom(@PathVariable Long id) {
    repository.deleteById(id);
  }
}

