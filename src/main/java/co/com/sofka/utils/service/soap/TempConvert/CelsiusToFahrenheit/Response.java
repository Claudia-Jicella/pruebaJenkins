package co.com.sofka.utils.service.soap.TempConvert.CelsiusToFahrenheit;

public enum Response {

    CONVERT_RESPONSE("<CelsiusToFahrenheitResult>%s</CelsiusToFahrenheitResult>");

    private final String value;

    Response(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }

}
