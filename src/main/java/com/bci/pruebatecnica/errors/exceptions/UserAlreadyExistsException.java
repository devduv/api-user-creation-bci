package com.bci.pruebatecnica.errors.exceptions;

import com.bci.pruebatecnica.utils.Constants;

public class UserAlreadyExistsException extends GenericException {
  public UserAlreadyExistsException() {
    super(Constants.USER_ALREADY_EXISTS);
  }
}
