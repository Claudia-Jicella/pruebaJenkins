package co.com.sofka.stepdefinition.services.soap.tempConvert.fahrenheittocelsius;


import co.com.sofka.models.fahrenheittocelsius.TempConvertG;
import co.com.sofka.stepdefinition.ServiceSetupFahrenheitToCelsius;
import co.com.sofka.models.fahrenheittocelsius.TempConvertF;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import static co.com.sofka.questions.RestumSoapServiceResponse.resturnSoapServiceResponse;
import static co.com.sofka.tasks.DoPost.doPost;
import static co.com.sofka.utils.FileUtilities.readFile;
import static co.com.sofka.utils.service.soap.TempConvert.FahrenheitToCelsius.Patch.CONVERT;
import static co.com.sofka.utils.service.soap.TempConvert.FahrenheitToCelsius.Response.CONVERT_RESPONSE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

public class FahrenheitToCelsiusStepDefinition extends ServiceSetupFahrenheitToCelsius {

    private static final Logger LOGGER = Logger.getLogger(FahrenheitToCelsiusStepDefinition.class);

    //Escenario Temperatura Ambiente
    private TempConvertF tempConvertF;
    @Given("que el usuario quiere investigar a que temperatura esta el ambiente en grados Celsius en Neiva")
    public void queElUsuarioQuiereInvestigarAQueTemperaturaEstaElAmbienteEnGradosCelsiusEnNeiva() {

        try {
            super.setUp();

        }catch (Exception exception){
            LOGGER.warn(exception.getMessage(), exception);
        }

    }

    @When("el usuario ingrese {int} grados Fahrenheit registrados actualmente en un termometro digital")
    public void elUsuarioIngreseGradosFahrenheitRegistradosActualmenteEnUnTermometroDigital(Integer conversionF) {

        try {
            tempConvertF = new TempConvertF();
            tempConvertF.setF(conversionF);
            actor.attemptsTo(
                    doPost()
                            .withTheResource(RESOURCE)
                            .andTheHeaders(super.headers())
                            .andTheBodyRequest(bodyRequestF())
            );
        }catch (Exception exception){
            LOGGER.warn(exception.getMessage(), exception);
        }

    }

    @Then("el usuario obtendra como respuesta {int} grados Celsius")
    public void elUsuarioObtendraComoRespuestaGradosCelsius(Integer resultadoF) {
        tempConvertF.setOutcomeF(resultadoF);

        try{
            actor.should(
                    seeThatResponse("El código de rspuesta HTTP debe ser: ",
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("El resultado de la conversion debe ser: ",
                            resturnSoapServiceResponse(),
                            containsString(bodyResponseF()))
            );

        } catch (Exception exception){
            LOGGER.warn(exception.getMessage(), exception);
        }
    }

    private TempConvertF tempConvertF(){
        return tempConvertF;
    }

    private String bodyRequestF(){
        return String.format(readFile(CONVERT.getValue()), tempConvertF().getF());
    }
    private String bodyResponseF(){
        return String.format(CONVERT_RESPONSE.getValue(), tempConvertF().getOutcomeF());
    }


    //Escenario MenosCero
    private TempConvertG tempConvertG;

    @Given("que el usuario quiere convertir grados Fahrenheit a Celsius")
    public void queElUsuarioQuiereConvertirGradosFahrenheitACelsius() {
        try {
            super.setUp();

        }catch (Exception exception){
            LOGGER.warn(exception.getMessage(), exception);
        }
    }

    @When("el usuario ingrese {int} grados Fahrenheit")
    public void elUsuarioIngreseGradosFahrenheit(Integer conversionG) {

        try {
            tempConvertG = new TempConvertG();
            tempConvertG.setG(conversionG);
            actor.attemptsTo(
                    doPost()
                            .withTheResource(RESOURCE)
                            .andTheHeaders(super.headers())
                            .andTheBodyRequest(bodyRequestG())
            );
        }catch (Exception exception){
            LOGGER.warn(exception.getMessage(), exception);
        }

    }

    @Then("el usuario obtendra como respuesta {int}")
    public void elUsuarioObtendraComoRespuesta(Integer resultadoG) {
        tempConvertG.setOutcomeG(resultadoG);

        try{
            actor.should(
                    seeThatResponse("El código de rspuesta HTTP debe ser: ",
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("El resultado de la conversion debe ser: ",
                            resturnSoapServiceResponse(),
                            containsString(bodyResponseG()))
            );

        } catch (Exception exception){
            LOGGER.warn(exception.getMessage(), exception);
        }

    }

    private TempConvertG tempConvertG(){
        return tempConvertG;
    }

    private String bodyRequestG(){
        return String.format(readFile(CONVERT.getValue()), tempConvertG().getG());

    }
    private String bodyResponseG(){
        return String.format(CONVERT_RESPONSE.getValue(), tempConvertG().getOutcomeG());

    }
}


