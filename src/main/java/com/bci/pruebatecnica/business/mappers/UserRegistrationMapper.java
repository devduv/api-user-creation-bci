package com.bci.pruebatecnica.business.mappers;

import com.bci.pruebatecnica.repository.entities.PhoneEntity;
import com.bci.pruebatecnica.repository.entities.UserEntity;
import com.bci.pruebatecnica.web.models.PhoneRequest;
import com.bci.pruebatecnica.web.models.UserRegistrationRequest;
import com.bci.pruebatecnica.web.models.UserRegistrationResponse;
import java.time.LocalDateTime;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class, Boolean.class})
public interface UserRegistrationMapper {

  @Mapping(target = "phoneEntities", qualifiedByName = "buildPhoneEntities",
      source = "request.phones")
  @Mapping(target = "created", expression = "java(LocalDateTime.now())")
  @Mapping(target = "modified", expression = "java(LocalDateTime.now())")
  @Mapping(target = "lastLogin", expression = "java(LocalDateTime.now())")
  @Mapping(target = "isActive", expression = "java(Boolean.TRUE)")
  UserEntity mapToUserEntity(UserRegistrationRequest request, String token);

  @Mapping(target = "isActive", source = "isActive")
  UserRegistrationResponse mapToUserRegistrationResponse(UserEntity entity);

  @Named("buildPhoneEntities")
  default List<PhoneEntity> buildPhoneEntities(List<PhoneRequest> phones) {
    return phones.stream()
        .map(phone -> PhoneEntity.builder()
            .number(phone.getNumber())
            .countryCode(phone.getCountryCode())
            .cityCode(phone.getCityCode()).build())
        .toList();
  }
}
