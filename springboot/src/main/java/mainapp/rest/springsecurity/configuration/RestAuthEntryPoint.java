package mainapp.rest.springsecurity.configuration;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * nadpisanie domyslnej odpowiedzi springsecurity security jezeli w zapytaniu rest nie ma danych uwierzytelniajacych lub gdy sa niewłaściwe
 * dodatkowo trzeba dorobic obsluge logowania przy pomocy danch z requesta (dla zwyklych uslug restowych nie ma GUI do logowania!!!)
 */
@Component
public class RestAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Użytkownik niezalogowany!!!");
    }

}
