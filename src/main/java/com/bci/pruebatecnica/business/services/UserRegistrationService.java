package com.bci.pruebatecnica.business.services;

import com.bci.pruebatecnica.web.models.UserRegistrationRequest;
import com.bci.pruebatecnica.web.models.UserRegistrationResponse;

public interface UserRegistrationService {

  UserRegistrationResponse registerUser(UserRegistrationRequest request);
}
