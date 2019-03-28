package mainapp.rest.spring.security.configuration;

import mainapp.rest.spring.security.providers.AuthProviderRest;
import mainapp.rest.spring.serwisy.SoupUiAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
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
@EnableWebSecurity
public class SpringSecurityConf extends WebSecurityConfigurerAdapter {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BasicAuthEntry basicAuthEntry;

    @Autowired
    private RestAuthEntryPoint restAuthEntryPoint;

    @Autowired
    private AuthProviderRest authProviderRest;

    /**
     * tworzenie przykladowych uzytkownikow ktorzy będa mogli korzystać z aplikacji
     * UWAGA -> w normalnych warunkach uzytkownicy by pochodzilij z bazy danych lub ldap
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //        auth.inMemoryAuthentication()
        //            .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADM")
        //            .and()
        //            .withUser("user1").password(passwordEncoder().encode("user1")).roles("USR");

        /**
         * rejestrowanie nowego providera do autorayzcji w spring security
         */
        auth.authenticationProvider(authProviderRest);
        logger.info("dodany nowy provider " + authProviderRest +" is configured: " + auth.isConfigured());
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
         * przyklad konfiguracji na basic authentication
         */
        //        http.authorizeRequests()
        //            .antMatchers("/testSecurity/**").permitAll()
        //            .antMatchers("/restowe/dlaZalogowanych").authenticated()
        //            .antMatchers("/restowe/dlaAdmina").hasRole("ADM")
        //            .and()
        //            .httpBasic()
        //            .authenticationEntryPoint(basicAuthEntry);
        /**
         * konfiguracja pod testy dzialania custom authentication providera
         * żeby dostarczyc login i haslo uzylem httpBasic
         * po podaniu loginu i haslo provider robi reszte!
         */
        http.authorizeRequests()
            .antMatchers("/testSecurity/stronaGlowna").permitAll()
            .antMatchers("/testSecurity/admin").hasRole("ADM")
            .antMatchers("/testSecurity/**").authenticated()
            .and()
            .httpBasic();
        logger.info("koniec konfiguracji spring security");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
