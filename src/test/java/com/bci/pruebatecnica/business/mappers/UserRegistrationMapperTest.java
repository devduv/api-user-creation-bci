package com.bci.pruebatecnica.business.mappers;

import static org.junit.jupiter.api.Assertions.*;

import com.bci.pruebatecnica.repository.entities.UserEntity;
import com.bci.pruebatecnica.web.models.PhoneRequest;
import com.bci.pruebatecnica.web.models.UserRegistrationRequest;
import com.bci.pruebatecnica.web.models.UserRegistrationResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class UserRegistrationMapperTest {

  private final UserRegistrationMapper userRegistrationMapper =
      Mappers.getMapper(UserRegistrationMapper.class);

  @Test
  void testMapToUserEntity() {
    UserRegistrationRequest request = UserRegistrationRequest.builder()
        .email("duvanbradbrid@gmail.com")
        .name("BRANDON DUVAN SAENZ FALCON")
        .password("$BrandonSaenz1")
        .phones(List.of(
            PhoneRequest.builder().number("993420344").cityCode("1").countryCode("51").build()))
        .build();

    UserEntity actual = userRegistrationMapper.mapToUserEntity(request, "123");

    assertEquals(request.getName(), actual.getName());
    assertNotNull(actual.getPhoneEntities());
  }

  @Test
  void testMapToUserRegistrationResponse() {
    UserEntity userEntity = UserEntity.builder()
        .email("duvanbradbrid@gmail.com")
        .name("BRANDON DUVAN SAENZ FALCON")
        .password("$BrandonSaenz1").isActive(true).build();

    UserRegistrationResponse actual = userRegistrationMapper
        .mapToUserRegistrationResponse(userEntity);

    assertNotNull(actual);
    assertTrue(actual.getIsActive());
  }
}