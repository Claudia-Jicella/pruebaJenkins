Feature: Conversión de temperatura Fahrenheit a Celsius
  Como usuario del sistema de Conversión de temperatura
  necesito utilizar la funcionalidad de convertir grados Fahrenheit a grados Celsius
  para obtener la temperatura ambiente


  @TemperaturaAmbiente
  Scenario: Temperatura ambiente de Neiva
    Given que el usuario quiere investigar a que temperatura esta el ambiente en grados Celsius en Neiva
    When el usuario ingrese 86 grados Fahrenheit registrados actualmente en un termometro digital
    Then el usuario obtendra como respuesta 30 grados Celsius


  @MenosCero
  Scenario: Conversion Menos Cero
    Given que el usuario quiere convertir grados Fahrenheit a Celsius
    When el usuario ingrese -13 grados Fahrenheit
    Then el usuario obtendra como respuesta -25

