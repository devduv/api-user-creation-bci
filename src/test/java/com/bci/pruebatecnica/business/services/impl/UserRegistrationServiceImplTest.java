package com.bci.pruebatecnica.business.services.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bci.pruebatecnica.business.PasswordValidationService;
import com.bci.pruebatecnica.business.mappers.UserRegistrationMapper;
import com.bci.pruebatecnica.errors.exceptions.PasswordPatternException;
import com.bci.pruebatecnica.errors.exceptions.PhonesEmptyException;
import com.bci.pruebatecnica.errors.exceptions.UserAlreadyExistsException;
import com.bci.pruebatecnica.repository.UserRepository;
import com.bci.pruebatecnica.repository.entities.UserEntity;
import com.bci.pruebatecnica.web.models.PhoneRequest;
import com.bci.pruebatecnica.web.models.UserRegistrationRequest;
import com.bci.pruebatecnica.web.models.UserRegistrationResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserRegistrationServiceImplTest {

  private final UserRegistrationMapper userRegistrationMapper =
      Mappers.getMapper(UserRegistrationMapper.class);
  @Mock
  private UserRepository userRepository;
  @Mock
  private PasswordValidationService passwordValidationService;
  @InjectMocks
  private UserRegistrationServiceImpl userRegistrationService;

  @Test
  void testRegisterUserOk() {
    UserRegistrationRequest request = buildUserRegistrationRequest();

    UserEntity userEntity = UserEntity.builder()
        .email("duvanbradbrid@gmail.com")
        .name("BRANDON DUVAN SAENZ FALCON")
        .password("$BrandonSaenz1").isActive(true).build();

    when(passwordValidationService.validatePassword(anyString())).thenReturn(true);
    when(userRepository.save(any())).thenReturn(userEntity);

    UserRegistrationResponse actual = userRegistrationService.registerUser(request);

    assertDoesNotThrow(() -> actual);
    assertTrue(actual.getIsActive());
  }

  @Test
  void testRegisterUserWhenPasswordNotValid() {
    UserRegistrationRequest request = buildUserRegistrationRequest();

    when(passwordValidationService.validatePassword(anyString()))
        .thenThrow(PasswordPatternException.class);

    assertThrows(PasswordPatternException.class,
        () -> userRegistrationService.registerUser(request));

    verify(userRepository, times(0)).save(any());
  }

  @Test
  void testRegisterUserWhenPasswordValidAndPhonesNot() {
    UserRegistrationRequest request = buildUserRegistrationRequest();
    request.setPhones(null);

    when(passwordValidationService.validatePassword(anyString())).thenReturn(true);

    assertThrows(PhonesEmptyException.class,
        () -> userRegistrationService.registerUser(request));

    verify(userRepository, times(0)).save(any());
  }

  @Test
  void testRegisterUserWhenEmailAlreadyExists() {
    UserRegistrationRequest request = buildUserRegistrationRequest();

    when(passwordValidationService.validatePassword(anyString())).thenReturn(true);
    when(userRepository.save(any())).thenThrow(RuntimeException.class);

    assertThrows(UserAlreadyExistsException.class,
        () -> userRegistrationService.registerUser(request));
  }

  private UserRegistrationRequest buildUserRegistrationRequest() {
    return UserRegistrationRequest.builder()
        .email("duvanbradbrid@gmail.com")
        .name("BRANDON DUVAN SAENZ FALCON")
        .password("$BrandonSaenz1")
        .phones(List.of(
            PhoneRequest.builder().number("993420344").cityCode("1").countryCode("51").build()))
        .build();
  }
}