package com.bci.pruebatecnica.errors;

import com.bci.pruebatecnica.errors.exceptions.GenericException;
import com.bci.pruebatecnica.web.models.ModelApiException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


  /**
   * Handle own exception response entity.
   *
   * @param exception exception
   * @return response entity
   */
  @ExceptionHandler({
      GenericException.class
  })
  public ResponseEntity<Object> handleOwnException(Exception exception) {
    return new ResponseEntity<>(
        ModelApiException.builder()
            .message(exception.getMessage()).build(),
        HttpStatus.BAD_REQUEST);
  }

  /**
   * Handle method argument not valid exception response entity.
   *
   * @param exception exception
   * @return response entity
   */
  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity<ModelApiException> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException exception) {

    BindingResult result = exception.getBindingResult();
    List<FieldError> fieldErrors = result.getFieldErrors();

    String message =
        String.format(
            "Campo '%s' %s", fieldErrors.get(0).getField(), fieldErrors.get(0).getDefaultMessage());

    return new ResponseEntity<>(
        ModelApiException.builder()
            .message(message).build(),
        HttpStatus.BAD_REQUEST);
  }
}
