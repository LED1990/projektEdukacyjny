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

import java.util.ArrayList;

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
     * wykonuje sie gdy dostarczone zostana informacje do logowania (np postem)
     * metoda musi zwracac UsernamePasswordAuthenticationToken
     * <p>
     * w obiekcie authenticate sa dane z formularza logowania (login i haslo)
     * domysle login i haslo sa przypiete do formularza do elementow o id odpowiednio -> username password
     * mozna nadpisac te identyfikatory ale trzeba to wtedy uwzglednic w konfiguracji spring security
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        logger.info("obiekt autoryzacji: " + authentication);
        User user = soupUiAuthService.pobierzDaneUzytkownika();  // do uslugi mozna przekazac login i haslo uzytkownika ktore sa w obiekcie authentication!
        Authentication auth = null;
        if (user != null) {
            //konstruktor automatycznie ustawia authenticated na true
            auth = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), new ArrayList<>());//new arraylist => pusta lista uprawnien
        }
        return auth;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
