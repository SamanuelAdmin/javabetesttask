package com.samanuel.auth_api.service.impl;

import java.util.UUID;
import org.springframework.stereotype.Service;
import com.samanuel.auth_api.service.AuthService;
import com.samanuel.auth_api.repository.UserRepository;
import com.samanuel.auth_api.repository.SessionRepository;
import com.samanuel.auth_api.model.User;
import com.samanuel.auth_api.model.Session;


public class AuthServiceImpl implements AuthService {
  private final UserRepository userRepository;
  private final SessionRepository sessionRepository;

  static public String hashPassword(String rawPassword) {
    return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
  }

  static public boolean checkPassword(String candidate, String hashed) {
    return BCrypt.checkpw(candidate, hashed);
  }
  

  public AuthController(UserRepository userRepository, SessionRepository sessionRepository) {
    this.repository = repository;
  }

  public String login(AuthEntity authEntity) {
    User user = this.userRepository.findByEmail(authEntity.email);

    if (!checkPassword(authEntity.password, user.hpass)) {
      throw new Exception("Passwords didnt match");
    }

    if (user.session != null) {
      return user.session.token;
    } else {
      Session new_session = new Session(
        UUID.randomUUID().toString(),
      );

      user.setSession(new_session);

      this.sessionRepository.save(new_session);
      return new_session.token;
    }
  }

  public void register(AuthEntity authEntity) {
    User newUser = new User(
      authEntity.email,
      authEntity.password
    );

    this.userRepository.save(newUser);
  }
}
