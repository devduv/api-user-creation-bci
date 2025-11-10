package com.bci.pruebatecnica.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
  public static final String USER_ALREADY_EXISTS = "El correo ya fue registrado anteriormente";
  public static final String PASSWORD_PATTERN =
      "La constraseña debe cumplir con el formato correcto";
  public static final String PHONES_EMPTY =
      "Debe haber al menos un elemento en la lista de números de celular";
  public static final String USER_SAVED_OK = "Usuario agregado correctamente";
}
