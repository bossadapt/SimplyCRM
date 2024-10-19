package SimplyCRM.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import SimplyCRM.entities.User;
import SimplyCRM.repository.ChatRoomRepository;
import SimplyCRM.repository.UserRepository;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(UserRepository userRepository,ChatRoomRepository chatRespository) {

    return args -> {
      /*
      {
        "email": "whitney@gmail.com",
        "password": "userpass"
     }
      */
      log.info("Preloading " + userRepository.save(new User("whitney@gmail.com", " $2a$12$VoiJ1jNHBtafSQixnLCltOHPfrXvKF2RFaZPYlIe/mk72ZzaVNU92 ", "Whitney Middle", "user")));
      /*
      {
        "email": "jimmy@gmail.com",
        "password": "adminpass"
     }
      */
      log.info("Preloading " + userRepository.save(new User("jimmy@gmail.com", "$2a$12$ssk9DglEZ3OINDRw0G0Ds.ogDXpzw14ZCdr92UKMYaTlSN7j7fGjG", "Jimmy Wrong", "admin")));
      /*
      {
        "email": "devin@gmail.com",
        "password": "ownerpass"
     }
      */
      log.info("Preloading " + userRepository.save(new User("devin@gmail.com", "$2a$12$cr9KmofWWKV/T9w/p6l7y.2/u0k7tKSZ3tdJKEFvHCXAvvJLij1eO", "Devin Right", "owner")));
    };
  }
}