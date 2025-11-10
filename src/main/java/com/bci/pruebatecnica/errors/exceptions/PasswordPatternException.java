package com.bci.pruebatecnica.errors.exceptions;

import com.bci.pruebatecnica.utils.Constants;

public class PasswordPatternException extends GenericException {
  public PasswordPatternException() {
    super(Constants.PASSWORD_PATTERN);
  }
}
