package co.com.sofka.stepdefinition.services.soap.tempConvert.celsiustofahrenheit;

import co.com.sofka.models.celsiustofahrenheit.TempConvertC;
import co.com.sofka.stepdefinition.ServiceSetupCelsiusToFahrenheit;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import static co.com.sofka.questions.RestumSoapServiceResponse.resturnSoapServiceResponse;
import static co.com.sofka.tasks.DoPost.doPost;
import static co.com.sofka.utils.FileUtilities.readFile;
import static co.com.sofka.utils.service.soap.TempConvert.CelsiusToFahrenheit.Patch.CONVERT;
import static co.com.sofka.utils.service.soap.TempConvert.CelsiusToFahrenheit.Response.CONVERT_RESPONSE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

public class CelsiusToFahrenheitStepDefinition extends ServiceSetupCelsiusToFahrenheit {

    private static final Logger LOGGER = Logger.getLogger(CelsiusToFahrenheitStepDefinition.class);

    //Escenario Congelacion
    private TempConvertC tempConvertC;

    @Given("que el usuario necesita saber el punto de congelacion del agua en grados Fahrenheit")
    public void queElUsuarioNecesitaSaberElPuntoDeCongelacionDelAguaEnGradosFahrenheit() {

        try {
            super.setup();
        }catch (Exception exception){
            LOGGER.warn(exception.getMessage(), exception);
        }

    }

    @When("el usuario digite {int} grados celsius y ejecute el calculo de conversión de temperatura")
    public void elUsuarioDigiteGradosCelsiusYEjecuteElCalculoDeConversionDeTemperatura(Integer conversionA) {

        try {
            tempConvertC = new TempConvertC();
            tempConvertC.setA(conversionA);
            actor.attemptsTo(
                    doPost()
                            .withTheResource(RESOURCE)
                            .andTheHeaders(super.headers())
                            .andTheBodyRequest(bodyRequestC())
            );
        }catch (Exception exception){
            LOGGER.warn(exception.getMessage(), exception);
        }


    }

    @Then("el usuario debera obtener como resultado {int} grados")
    public void elUsuarioDeberaObtenerComoResultadoGrados(Integer resultadoC) {
        tempConvertC.setOutcome(resultadoC);

        try{
            actor.should(
                    seeThatResponse("El código de rspuesta HTTP debe ser: ",
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("El resultado de la conversion debe ser: ",
                            resturnSoapServiceResponse(),
                            containsString(bodyResponseC()))
                    );

        } catch (Exception exception){
            LOGGER.warn(exception.getMessage(), exception);
        }

    }

    private TempConvertC tempConvertC(){
        return tempConvertC;
    }


    private String bodyRequestC(){
        return String.format(readFile(CONVERT.getValue()), tempConvertC().getA());


    }
    private String bodyResponseC(){
        return String.format(CONVERT_RESPONSE.getValue(), tempConvertC().getOutcome());

    }

    //Escenario Ebullicion

    @Given("que el usuario quiere conocer punto de ebullición en grados Fahrenheit")
    public void queElUsuarioQuiereConocerPuntoDeEbullicionEnGradosFahrenheit() {
        super.setup();
    }

    @When("el usuario ingrese {int} grados celsius")
    public void elUsuarioIngreseGradosCelsius(Integer conversionB) {
        tempConvertC = new TempConvertC();
        tempConvertC.setA(conversionB);
        
        actor.attemptsTo(
                doPost()
                        .withTheResource(RESOURCE)
                        .andTheHeaders(super.headers())
                        .andTheBodyRequest(bodyRequestE())
        );

    }

    @Then("obtendra como resultado {int} grados Fahrenheit")
    public void obtendraComoResultadoGradosFahrenheit(Integer resultadoE) {

        tempConvertC.setOutcome(resultadoE);
        actor.should(
                seeThatResponse("El código de rspuesta HTTP debe ser: ",
                        response -> response.statusCode(HttpStatus.SC_OK)),
                seeThat("El resultado de la conversion debe ser: ",
                        resturnSoapServiceResponse(),
                        containsString(bodyResponseE()))

        );

    }

    private TempConvertC tempConvertE() {
        return tempConvertC;
    }

    private String bodyRequestE (){
        return String.format(readFile(CONVERT.getValue()), tempConvertE().getA());

    }

    private String bodyResponseE (){
        return String.format(CONVERT_RESPONSE.getValue(), tempConvertE().getOutcome());

    }
}
