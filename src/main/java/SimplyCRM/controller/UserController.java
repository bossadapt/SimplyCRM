package SimplyCRM.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import SimplyCRM.security.UserPrincipal;
import SimplyCRM.entities.User;
import SimplyCRM.entities.UserNotFoundException;
import SimplyCRM.model.UserIdentity;
import SimplyCRM.repository.UserRepository;

@CrossOrigin
@RestController
@EntityScan("SimplyCRM.entities*")  
public class UserController {
    private final UserRepository repository;

    UserController(UserRepository repository) {
      this.repository = repository;
}
  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/users")
  List<UserIdentity> allUserNames() {
    List<UserIdentity> test = new ArrayList<UserIdentity>();
    repository.findAll().forEach((user)-> test.add(user.toUserIdentity()));
    return test;
  }
  // end::get-aggregate-root[]

  @PostMapping("/users")
  User newUser(@RequestBody User newUser) {
    return repository.save(newUser);
  }
  @GetMapping("/users/self")
  User userByAuth(@AuthenticationPrincipal UserPrincipal principal){
    return repository.findByEmail(principal.getEmail()).orElseThrow();
  }
  // Single item
  
  @GetMapping("/users/{id}")
  User userById(@PathVariable Long id,@AuthenticationPrincipal UserPrincipal principal) {
    
    return repository.findById(id)
      .orElseThrow(() -> new UserNotFoundException(id));
  }

  @PutMapping("/users/{id}")
  User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(user -> {
        user.setName(newUser.getName());
        user.setTitle(newUser.getTitle());
        return repository.save(user);
      })
      .orElseGet(() -> {
        return repository.save(newUser);
      });
  }

  @DeleteMapping("/users/{id}")
  void deleteUser(@PathVariable Long id) {
    repository.deleteById(id);
  }
}