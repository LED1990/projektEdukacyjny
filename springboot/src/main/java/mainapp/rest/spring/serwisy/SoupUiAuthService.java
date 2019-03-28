package mainapp.rest.spring.serwisy;

import mainapp.rest.spring.model.DaneUzytkownika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * tesotwy klient do pobierania danych uzytkownika z uslugi mockowanej na soap ui
 */
@Service
public class SoupUiAuthService{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${rest.dane.uzytkowanika.url}")
    private String restDaneUztykownikaUrl;

    private final RestTemplate restTemplate;

    public SoupUiAuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * usluga restowa biera dane o uzytkowniku z mocka
     * @return
     * @throws UsernameNotFoundException
     */
    public User pobierzDaneUzytkownika() throws UsernameNotFoundException {
        logger.info("pobieranie danych uzytkownika z uslugi rest");
        DaneUzytkownika daneUzytkownika = restTemplate.getForObject(restDaneUztykownikaUrl, DaneUzytkownika.class);
        if (daneUzytkownika != null) {
            return new User(daneUzytkownika.getImie(), daneUzytkownika.getUuid(), new ArrayList<>());
        }
        return null;
    }
}
