package SimplyCRM.entities;


import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import SimplyCRM.model.UserIdentity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {
  private @Id
  @GeneratedValue Long id;
  private String email;
  @JsonIgnore
  private String password;
  private String name;
  private String title;
  private Long[] chatRooms;
  //DEFAULT CONSTRUCTOR... LEAVE OR AUTHENTICATION FAILS
  public User(){
    super();
 }
  public User(String email, String password, String name, String title) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.title = title;
    this.chatRooms = new Long[0];
  }

  public UserIdentity toUserIdentity(){
    return UserIdentity.builder().id(id).name(name).build();
  }
  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof User))
      return false;
    User user = (User) o;
    return Objects.equals(this.id, user.id) && Objects.equals(this.name, user.name)
        && Objects.equals(this.title, user.title);
  }
  public void addChatRoom(Long newChatRoomId){
    Long[] newChatRooms = new Long[this.chatRooms.length+1];
    for(int i =0; i< this.chatRooms.length;i++){
      newChatRooms[i] = this.chatRooms[i];
    }
    newChatRooms[this.chatRooms.length] = newChatRoomId;
  }
  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name, this.title);
  }

  @Override
  public String toString() {
    return "User{" + "id=" + this.id + ", name='" + this.name + '\'' + ", title='" + this.title + '\'' + '}';
  }
}
