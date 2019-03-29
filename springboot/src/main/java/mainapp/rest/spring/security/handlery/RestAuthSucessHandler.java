package mainapp.rest.spring.security.handlery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * do obslugi sukcesu podczas logowania
 */
@Component
public class RestAuthSucessHandler implements AuthenticationSuccessHandler{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        logger.info("autoryzacja zakonczona sukcesem");
        handle(httpServletRequest, httpServletResponse);
    }

    private void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        String homeUrl = "/testSecurity/stronaGlowna";
        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, homeUrl);
    }
}
