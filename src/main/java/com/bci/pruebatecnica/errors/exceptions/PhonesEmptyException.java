package com.bci.pruebatecnica.errors.exceptions;

import com.bci.pruebatecnica.utils.Constants;

public class PhonesEmptyException extends GenericException {
  public PhonesEmptyException() {
    super(Constants.PHONES_EMPTY);
  }
}
