package com.samanuel.auth_api.service;

import com.samanuel.auth_api.entity.ProcessEntity;
import com.samanuel.auth_api.model.User;


public interface ProcessService {
  String process(User user, ProcessEntity processEntity) throws Exception;
}
