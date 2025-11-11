package com.bci.pruebatecnica.business.services.impl;

import com.bci.pruebatecnica.business.PasswordValidationService;
import com.bci.pruebatecnica.business.mappers.UserRegistrationMapper;
import com.bci.pruebatecnica.business.services.UserRegistrationService;
import com.bci.pruebatecnica.errors.exceptions.PhonesEmptyException;
import com.bci.pruebatecnica.errors.exceptions.UserAlreadyExistsException;
import com.bci.pruebatecnica.repository.UserRepository;
import com.bci.pruebatecnica.repository.entities.UserEntity;
import com.bci.pruebatecnica.utils.Constants;
import com.bci.pruebatecnica.utils.JwtUtil;
import com.bci.pruebatecnica.web.models.UserRegistrationRequest;
import com.bci.pruebatecnica.web.models.UserRegistrationResponse;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserRegistrationServiceImpl implements UserRegistrationService {

  private final UserRepository userRepository;
  private final PasswordValidationService passwordValidationService;
  private final UserRegistrationMapper userRegistrationMapper =
      Mappers.getMapper(UserRegistrationMapper.class);

  /**
   * Register user. Validates password format and email doesn't exist.
   *
   * @param request user registration request
   * @return user registration response
   */
  @Override
  public UserRegistrationResponse registerUser(UserRegistrationRequest request) {
    return Optional.of(request)
        .filter(data -> passwordValidationService.validatePassword(data.getPassword()))
        .filter(this::validatePhones)
        .map(this::buildUserEntity)
        .map(this::saveUser)
        .map(userRegistrationMapper::mapToUserRegistrationResponse)
        .orElseThrow();
  }

  /**
   * Build user entity.
   *
   * @param request user registration request
   * @return user entity
   */
  private UserEntity buildUserEntity(UserRegistrationRequest request) {
    String token = JwtUtil.generateToken(request.getEmail());
    UserEntity userEntity = userRegistrationMapper.mapToUserEntity(request, token);
    userEntity.getPhoneEntities().forEach(phone -> phone.setUser(userEntity));
    return userEntity;
  }

  /**
   * Validate phones.
   *
   * @param request user registration request
   * @return true if phone has items
   */
  private boolean validatePhones(UserRegistrationRequest request) {
    if (request.getPhones() != null && !request.getPhones().isEmpty()) {
      return true;
    }
    throw new PhonesEmptyException();
  }

  /**
   * Save user.
   *
   * @param entity user entity
   * @return optional user entity
   */
  private UserEntity saveUser(UserEntity entity) {
    try {
      UserEntity saved = userRepository.save(entity);
      log.info(Constants.USER_SAVED_OK);
      return saved;
    } catch (Exception ex) {
      log.error(Constants.USER_ALREADY_EXISTS);
      throw new UserAlreadyExistsException();
    }
  }
}
