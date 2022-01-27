Feature: Conversión de temperatura Celsius a Fahrenheit
  Como usuario del sistema de Conversión de temperatura
  necesito validar la funcionalidad de convertir grados Celsius a grados Fahrenheit
  para obtener las temperaturas de congelación y ebullición del agua en Fahrenheit

  @Congelacion
  Scenario: Congelación del agua
    Given que el usuario necesita saber el punto de congelacion del agua en grados Fahrenheit
    When el usuario digite 0 grados celsius y ejecute el calculo de conversión de temperatura
    Then el usuario debera obtener como resultado 32 grados

  @Ebullicion
  Scenario: Ebullición  del agua
    Given que el usuario quiere conocer punto de ebullición en grados Fahrenheit
    When el usuario ingrese 100 grados celsius
    Then obtendra como resultado 212 grados Fahrenheit