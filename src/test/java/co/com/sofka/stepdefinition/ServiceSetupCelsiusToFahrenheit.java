package co.com.sofka.stepdefinition;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.apache.log4j.PropertyConfigurator;
import java.util.HashMap;
import static co.com.sofka.utils.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;

public class ServiceSetupCelsiusToFahrenheit {

    protected static final String URL_BASE = "https://www.w3schools.com";
    protected static final String RESOURCE = "/xml/tempconvert.asmx?wsdl";
    protected final Actor actor = new Actor("Claudia");

    protected void setup() {
        setUpLog4j();
        actorCan();
    }

    private void actorCan(){
        actor.can(CallAnApi.at(URL_BASE));
    }

    protected HashMap<String, Object> headers(){
        HashMap<String, Object> headerCollection = new HashMap<>();
        headerCollection.put("Content-Type", "text/xml;charset=UTF-8");
        headerCollection.put("SOAPAction", "https://www.w3schools.com/xml/CelsiusToFahrenheit");
        return headerCollection;
    }

    private void setUpLog4j(){
        PropertyConfigurator.configure(System.getProperty("user.dir")+ LOG4J_PROPERTIES_FILE_PATH.getValue());

    }


}
