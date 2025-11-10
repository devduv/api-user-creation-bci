package com.bci.pruebatecnica.business.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.bci.pruebatecnica.errors.exceptions.PasswordPatternException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class PasswordValidationServiceImplTest {

  @InjectMocks
  private PasswordValidationServiceImpl passwordValidationService;

  @Test
  void testValidatePasswordOk() {
    ReflectionTestUtils.setField(passwordValidationService, "passwordPattern",
        "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._-])[A-Za-z\\d@$!%*?&._-]{6,15}$");

    boolean actual = passwordValidationService.validatePassword("$Brandon260998");

    assertTrue(actual);
  }

  @Test
  void testValidatePasswordFail() {
    ReflectionTestUtils.setField(passwordValidationService, "passwordPattern",
        "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._-])[A-Za-z\\d@$!%*?&._-]{6,15}$");

    assertThrows(PasswordPatternException.class,
        () -> passwordValidationService.validatePassword("Brandon"));
  }
}