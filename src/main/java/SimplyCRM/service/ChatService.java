package SimplyCRM.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import SimplyCRM.entities.ChatRoom;
import SimplyCRM.entities.User;
import SimplyCRM.model.BasicResponse;
import SimplyCRM.repository.ChatRoomRepository;
import SimplyCRM.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    //TODO: make this go off IDs that the user feeds in based on the choice they make
    public BasicResponse createChatRoom(String chatName, List<Long> participantIDs){
        System.out.println("newChatMaker: name="+chatName+", list:"+participantIDs.toString());
        if(chatName == null || chatName.trim().isEmpty()){
            return BasicResponse.builder().sucessful(false).text("invalid: empty name").build();
        }
        if(participantIDs == null || participantIDs.size() ==0){
            return BasicResponse.builder().sucessful(false).text("invalid: empty list").build();
        }
        List<User> userList = new ArrayList<>();
        try{
            for(int i=0; i<participantIDs.size();i++){
                userList.add(userRepository.getReferenceById(participantIDs.get(i)));
            }
        }catch(Exception e){
            return BasicResponse.builder().sucessful(false).text("invalid: participant list have invalid users").build();
        }

        ChatRoom newChatroom = new ChatRoom(chatName, participantIDs);
        for(int i=0; i<userList.size();i++){
            userList.get(i).addChatRoom(null);
        }
        try{
            chatRoomRepository.save(newChatroom);
            userRepository.saveAll(userList);
        }catch(Exception e){
            return BasicResponse.builder().sucessful(false).text(e.toString()).build();
        }
        return BasicResponse.builder().sucessful(true).text(newChatroom.getId().toString()).build();
        
    }
}
