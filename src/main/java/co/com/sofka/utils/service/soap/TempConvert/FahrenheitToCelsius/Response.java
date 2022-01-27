package co.com.sofka.utils.service.soap.TempConvert.FahrenheitToCelsius;

public enum Response {

    CONVERT_RESPONSE("<FahrenheitToCelsiusResult>%s</FahrenheitToCelsiusResult>");

    private final String value;

    Response(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }

}
