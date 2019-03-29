package mainapp.rest.spring.security.configuration;

import mainapp.rest.spring.security.handlery.RestAuthFailHandler;
import mainapp.rest.spring.security.handlery.RestAuthSucessHandler;
import mainapp.rest.spring.security.providers.AuthProviderRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * WebSecurityConfigurerAdapter -> to jest filtr zapytan dla spring security
 */
@Configuration
@EnableWebSecurity(debug = false)
public class SpringSecurityConf extends WebSecurityConfigurerAdapter {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AuthProviderRest authProviderRest;

    @Autowired
    private RestAuthSucessHandler restAuthSucessHandler;

    @Autowired
    private RestAuthFailHandler restAuthFailHandler;

    /**
     * tworzenie przykladowych uzytkownikow ktorzy będa mogli korzystać z aplikacji
     * UWAGA -> w normalnych warunkach uzytkownicy by pochodzilij z bazy danych lub ldap
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * rejestrowanie nowego providera do autorayzcji w spring security
         * pozwala na zrobienie customowej logiki logowania
         */
        auth.authenticationProvider(authProviderRest);
        logger.info("dodany nowy provider " + authProviderRest + " is configured: " + auth.isConfigured());
    }

    /**
     * konfiguracja ochrony endpointow aplikacji
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * konfiguracja pod testy dzialania custom authentication providera
         * login i haslo sa dostarczane z fotmularza logowania
         * provider robi reszte autoryzacji!
         */
        http.csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/testSecurity/admin").hasAuthority("ADM")
            .antMatchers("/testSecurity/logowanie").permitAll()
            .antMatchers("/testSecurity/**").authenticated()
            .and()
            .formLogin()
            .loginPage("/testSecurity/logowanie")
            .loginProcessingUrl("/testSecurity/perform_logowanie")//to laczy formularz logowani z spring security -> bez tego nie wywola sie authentication provider
            .defaultSuccessUrl("/testSecurity/stronaGlowna", true)//tu przekieruje jak autoryzacja bedzie ok
            .successHandler(restAuthSucessHandler)//kolejnosc ma znaczenie!! ustawienie handelra po defaultSuccessUrl spowoduje że domyślna strona zostanie zignorowana! jeżeli hendler by byl wyzej to jego metoda by sie nie wykonala ! to samo z failure handlerem!
            //            .usernameParameter("jakas wartosc")//pozwala nadpisac id pola nazwy uzytkownika z customowego formularza logowania -> domyslnie jest username
            .failureUrl("/testSecurity/logowanie?error=true")
            .failureHandler(restAuthFailHandler)
            .and()
            .logout()
            .logoutUrl("/testSecurity/perform_wylogowanie")
            .deleteCookies("JSESSIONID");

        logger.info("koniec konfiguracji spring security");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
