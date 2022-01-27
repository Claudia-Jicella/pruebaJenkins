package co.com.sofka.runner.services.soap.tempConvert.celsiusToFahrenheit.test;

import co.com.sofka.models.celsiustofahrenheit.TempConvertC;
import co.com.sofka.stepdefinition.ServiceSetupCelsiusToFahrenheit;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static co.com.sofka.questions.RestumSoapServiceResponse.resturnSoapServiceResponse;
import static co.com.sofka.tasks.DoPost.doPost;
import static co.com.sofka.utils.FileUtilities.readFile;
import static co.com.sofka.utils.service.soap.TempConvert.CelsiusToFahrenheit.Patch.CONVERT;
import static co.com.sofka.utils.service.soap.TempConvert.CelsiusToFahrenheit.Response.CONVERT_RESPONSE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(SerenityRunner.class)
public class TempConvertTest extends ServiceSetupCelsiusToFahrenheit {


    @Before
    public void setUp(){
        super.setup();

    }

    @Test
    public void convertCelsiusToFahrenheit(){
        actor.attemptsTo(
                doPost()
                        .withTheResource(RESOURCE)
                        .andTheHeaders(super.headers())
                        .andTheBodyRequest(bodyRequest())
        );

        actor.should(
                seeThatResponse("El código de rspuesta HTTP debe ser: ",
                        response -> response.statusCode(HttpStatus.SC_OK)),
                seeThat("El resultado de la conversion debe ser: ",
                        resturnSoapServiceResponse(),
                        containsString(bodyResponse()))

        );

    }

    private TempConvertC tempConvert(){
        TempConvertC temCom = new TempConvertC();
        temCom.setA(500);
        temCom.setOutcome(932);
        return temCom;
    }

    private String bodyRequest (){
        return String.format(readFile(CONVERT.getValue()), tempConvert().getA());

    }

    private String bodyResponse (){
        return String.format(CONVERT_RESPONSE.getValue(), tempConvert().getOutcome());

    }

}
