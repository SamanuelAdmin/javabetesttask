package com.samanuel.data_api.service;

import com.samanuel.data_api.repository.QueryRepository;
import org.springframework.stereotype.Service;
import com.samanuel.data_api.model.Query;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;



@Service
public class MainService {
  private final String internalToken = System.getenv("INTERNAL_TOKEN");
  @Autowired private QueryRepository repository;

  public boolean checkToken(String token) {
    Map<String, String> env = System.getenv();

    for (String envName : env.keySet()) {
        System.out.println(envName + "=" + env.get(envName));
    }

    System.out.println(token);
    System.out.println(internalToken);

    if (internalToken == null) {
      return false;
    }

    return internalToken.equals(token);
  }

  public String process(String data) {
    StringBuffer sbf = new StringBuffer(data);
    sbf.reverse();

    Query q = new Query();
    q.setQuery(data);
    q.setProcessed(sbf.toString());
    this.repository.save(q);

    return sbf.toString();
  }
}
