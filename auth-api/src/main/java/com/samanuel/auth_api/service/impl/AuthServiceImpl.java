package com.samanuel.auth_api.service.impl;

import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.samanuel.auth_api.entity.AuthEntity;
import com.samanuel.auth_api.model.Session;
import com.samanuel.auth_api.model.User;
import com.samanuel.auth_api.repository.SessionRepository;
import com.samanuel.auth_api.repository.UserRepository;
import com.samanuel.auth_api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class AuthServiceImpl implements AuthService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private SessionRepository sessionRepository;

  static public String hashPassword(String rawPassword) {
    return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
  }

  static public boolean checkPassword(String candidate, String hashed) {
    return BCrypt.checkpw(candidate, hashed);
  }
  

  public void AuthController(UserRepository userRepository, SessionRepository sessionRepository) {
    this.userRepository = userRepository;
    this.sessionRepository = sessionRepository;
  }

  public String login(AuthEntity authEntity) throws Exception {
    User user = this.userRepository.findByEmail(authEntity.getEmail());

    System.out.println(user);
    System.out.println(authEntity);
    if (!checkPassword(authEntity.getPassword(), user.getHpass())) {
      throw new Exception("Passwords didnt match");
    }

    if (user.getSession() != null) {
      return user.getSession().getToken();
    } else {
      Session newSession = new Session();
      newSession.setToken(UUID.randomUUID().toString());

      user.setSession(newSession);

      this.sessionRepository.save(newSession);
      return newSession.getToken();
    }
  }


  public void register(AuthEntity authEntity) {
    User newUser = new User();
    newUser.setEmail(authEntity.getEmail());
    newUser.setHpass(
      this.hashPassword(
        authEntity.getPassword()
      )
    );

    this.userRepository.save(newUser);
  }

  
  public User checkSession(String token) {
    // will throw exeption if not found
    Session session = this.sessionRepository.findByToken(token);

    // TODO: Add timestamp and exp time checker
  }
}
