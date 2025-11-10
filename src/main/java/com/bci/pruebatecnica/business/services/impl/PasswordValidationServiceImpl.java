package com.bci.pruebatecnica.business.services.impl;

import com.bci.pruebatecnica.business.PasswordValidationService;
import com.bci.pruebatecnica.errors.exceptions.PasswordPatternException;
import com.bci.pruebatecnica.utils.Constants;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PasswordValidationServiceImpl implements PasswordValidationService {

  @Value("${password-pattern}")
  private String passwordPattern;

  /**
   * Validate password by password pattern.
   *
   * @param password password
   * @return true if password is valid
   */
  @Override
  public boolean validatePassword(String password) {
    Pattern pattern = Pattern.compile(passwordPattern);
    Matcher matcher = pattern.matcher(password);
    if (matcher.matches()) {
      return true;
    }
    log.error("Error: {}", Constants.PASSWORD_PATTERN);
    throw new PasswordPatternException();
  }
}
