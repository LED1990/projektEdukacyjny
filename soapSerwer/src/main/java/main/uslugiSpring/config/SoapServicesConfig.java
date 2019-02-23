package main.uslugiSpring.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;

@EnableWs
@Configuration
public class SoapServicesConfig {

    /**
     * Tworząc serwer soap z springiem trzeba zarejestrować dispacher servlet do obsługi zapytań soap
     * spring ma do tego  oddzielny servlet(inny niż dispacher servlet)
     * @param applicationContext
     * @return
     */
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispacherServlet(ApplicationContext applicationContext){
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(applicationContext);
        messageDispatcherServlet.setTransformSchemaLocations(true);
        return new ServletRegistrationBean<>(messageDispatcherServlet, "/ws-spring/*");
    }

    /**
     * wystawinie wsdla
     * nazwa bena stanowi fragment url pod ktorym bedzie dostepny wsdl
     * w tym przypadku adres wsdl to: adr_serwera:port/ws/greetingsWsdl.wsdl
     * ws w url pochodzi z mappingu dispacher servleta wyżej zdefiniowanego
     * @return
     */
    @Bean(name = "greetingsWsdl")
    public Wsdl11Definition defaultWsdl11Definition(){
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
        wsdl11Definition.setWsdl(new ClassPathResource("/wsdl/greetings.wsdl"));
        return  wsdl11Definition;
    }
}
