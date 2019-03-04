package cxfrestwebservlet;

import org.apache.cxf.jaxrs.servlet.CXFNonSpringJaxrsServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * uruchamiajac uslugę na JBOSS trzeba pamiętać o dopisuaniu kontekstu aplikacji do url!!!
 * kontekst to nazwa wara + wesja jeśli nie została ustawiona nazwa wara ręcznie
 * ja mam ustawioną ręcznie nazwę wara
 *
 * na tomkacie wystarczy podać mapowanie z konfiguracji (bez kontekstu aplikacji)
 */

@WebServlet(name = "cxfGreeting", urlPatterns = "/cxf/*", loadOnStartup = 1, initParams = {
        @WebInitParam(name = "jaxrs.serviceClasses", value = "cxfrestwebservlet.GreetingsCxfSerwis"),
        @WebInitParam(name = "jaxrs.providers", value = "cxfrestwebservlet.GreetingsCxfSerwis")
})
public class CxfSerwlet extends CXFNonSpringJaxrsServlet {

}
