package mainapp.rest.spring.security.providers;

import mainapp.rest.spring.serwisy.SoupUiAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

/**
 * ten provider ma wspolpracowac z mockowana usluga restowa z soapui
 * <p>
 * AuthenticationProvider pozwala na stosowanie doadtkowych opcji autoryzacji uzytkownika (zewnetrzna usluga itp)
 * zajemuje się on procesowanie requestow autoryzacji
 * UWAGA! tutaj się wchodzi dopiero po tym jak usluga autoryzacji da odpowidz!
 */
@Component
public class AuthProviderRest implements AuthenticationProvider {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SoupUiAuthService soupUiAuthService;

    public AuthProviderRest() {
    }

    /**
     * wykonuje sie gdy dostarczone zostana informacje do logowania
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        logger.info("wywołanie usługi rest w celu pobranie informacji o użytkowniku");
        logger.info("obiekt autoryzacji: " + authentication);
        /**
         * na tym etapie mam juz dane uzytkownika z uslugi restowej
         */
        User user = soupUiAuthService.pobierzDaneUzytkownika();
        //todo trzeba zworcic obiekt authentication + ustawić flagi (authenticated - jezeli jest ok wszystko itp)
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
