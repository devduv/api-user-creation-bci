package com.bci.pruebatecnica.web.impl;

import com.bci.pruebatecnica.business.services.UserRegistrationService;
import com.bci.pruebatecnica.web.UsersApi;
import com.bci.pruebatecnica.web.models.UserRegistrationRequest;
import com.bci.pruebatecnica.web.models.UserRegistrationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UsersApiImpl implements UsersApi {

  private final UserRegistrationService userRegistrationService;

  /**
   * Register user.
   *
   * @param request (required)
   * @return Create user successfully with status code 201
   */
  @Override
  public ResponseEntity<UserRegistrationResponse> registerUser(
      UserRegistrationRequest request) {
    UserRegistrationResponse response = userRegistrationService.registerUser(request);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
