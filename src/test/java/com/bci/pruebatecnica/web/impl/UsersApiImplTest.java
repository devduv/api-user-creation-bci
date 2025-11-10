package com.bci.pruebatecnica.web.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.bci.pruebatecnica.business.services.UserRegistrationService;
import com.bci.pruebatecnica.web.models.PhoneRequest;
import com.bci.pruebatecnica.web.models.UserRegistrationRequest;
import com.bci.pruebatecnica.web.models.UserRegistrationResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class UsersApiImplTest {

  @Mock
  private UserRegistrationService userRegistrationService;

  @InjectMocks
  private UsersApiImpl usersApi;

  @Test
  void testRegisterUserOk() {

    UserRegistrationRequest request = UserRegistrationRequest.builder()
        .email("duvanbradbrid@gmail.com")
        .name("BRANDON DUVAN SAENZ FALCON")
        .password("$BrandonSaenz1")
        .phones(List.of(
            PhoneRequest.builder().number("993420344").cityCode("1").countryCode("51").build()))
        .build();

    UserRegistrationResponse response = UserRegistrationResponse.builder()
        .token("jwt").id("123456").build();

    when(userRegistrationService.registerUser(any())).thenReturn(response);
    ResponseEntity<UserRegistrationResponse> actual = usersApi.registerUser(request);
    assertDoesNotThrow(() -> actual);
    assertEquals(201, actual.getStatusCode().value());
  }
}