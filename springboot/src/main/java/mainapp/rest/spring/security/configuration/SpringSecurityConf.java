package mainapp.rest.spring.security.configuration;

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
@EnableWebSecurity
public class SpringSecurityConf extends WebSecurityConfigurerAdapter{

    @Autowired
    private BasicAuthEntry basicAuthEntry;

    @Autowired
    private RestAuthEntryPoint restAuthEntryPoint;

    @Autowired
    private AuthSukcess authSukcess;
    /**
     * tworzenie przykladowych uzytkownikow ktorzy będa mogli korzystać z aplikacji
     * UWAGA -> w normalnych warunkach uzytkownicy by pochodzilij z bazy danych lub ldap
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADM")
            .and()
            .withUser("user1").password(passwordEncoder().encode("user1")).roles("USR");
    }

    /**
     * konfiguracja ochrony endpointow aplikacji
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * nie wiem jak to przetestowa na postmanie!
         * jaki rodzaj autoryzacji wybrac?
         */
        http.csrf().disable()
            .exceptionHandling()
            .authenticationEntryPoint(restAuthEntryPoint)
            .and()
            .authorizeRequests()
            .antMatchers("/restowe/dlaZalogowanych").authenticated()
            .antMatchers("/restowe/dlaAdmina").hasRole("ADM")
            .and()
            .formLogin()
            .successHandler(authSukcess)
            .and()
            .logout();
        /**
         * przyklad konfiguracji na basic authentication
         */
//        http.authorizeRequests()
//            .antMatchers("/restowe/dlaZalogowanych").authenticated()
//            .antMatchers("/restowe/dlaAdmina").hasRole("ADM")
//            .and()
//            .httpBasic()
//            .authenticationEntryPoint(basicAuthEntry);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
