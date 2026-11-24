package com.samanuel.auth_api.service.impl;

import com.samanuel.auth_api.entity.ProcessEntity;
import com.samanuel.auth_api.model.User;
import org.springframework.stereotype.Service;
import com.samanuel.auth_api.service.ProcessService;

import org.json.simple.JSONValue;
import org.json.simple.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Service
public class ProcessServiceImpl implements ProcessService {
  private static String PROMPT_TEMPLATE =  "Hello, %s!\n%s";
  private static String TARGET_URL = System.getenv("INTERNAL_SERVICE_ENDPOINT");

  private String makeRequest(String prompt) throws Exception {
    // FROM https://stackoverflow.com/questions/1359689/how-to-send-http-request-in-java
    // MAKE STACKOVERFLOW GREAT AGAIN
    System.out.println(TARGET_URL);
    
    String result = null;
    HttpURLConnection connection = null;

    try {
      //Create connection
      URL url = new URL(TARGET_URL);
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type", 
          "application/json");
      connection.setRequestProperty("X-Internal-Token", 
          System.getenv("INTERNAL_TOKEN"));

      connection.setUseCaches(false);
      connection.setDoOutput(true);

      //Send request
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("text", prompt);

      DataOutputStream wr = new DataOutputStream (
          connection.getOutputStream());
      wr.writeBytes(jsonObject.toString());
      wr.close();

      int status = connection.getResponseCode();

      //Get Response  
      InputStream is = (status > 399) 
        ? connection.getErrorStream()
        : connection.getInputStream();
      BufferedReader rd = new BufferedReader(new InputStreamReader(is));

      // build the response
      StringBuilder response = new StringBuilder();
      String line;
      while ((line = rd.readLine()) != null) {
        response.append(line);
        response.append('\r');
      }
      rd.close();

      if (status > 299 || status < 200) {
        throw new Exception(
            String.format("Cannot process %s - code %d\nGot: %s", TARGET_URL, status, response.toString())
          );
      }

      // return response.toString();
      JSONObject resObj = (JSONObject) JSONValue.parse(response.toString());
      return (String) resObj.get("result");
    } catch (Exception e) {
      System.err.println(
          String.format("Got exception while processing: %s", e.toString())  
        );

      throw e;
    } finally {
      if (connection != null) {
        connection.disconnect();
      }
    }
  }


  public String process(User user, ProcessEntity processEntity) throws Exception {
    String prompt = String.format(
      this.PROMPT_TEMPLATE, user.getEmail(), processEntity.getText()
    );

    String response = null;

    try {
      response = this.makeRequest(prompt);
    } catch (Exception e) {
      throw e;
    }

    return response;
  }
}
