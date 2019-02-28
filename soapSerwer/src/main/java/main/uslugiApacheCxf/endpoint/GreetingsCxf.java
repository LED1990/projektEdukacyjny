package main.uslugiApacheCxf.endpoint;

import main.uslugiApacheCxf.config.CxfConfig;
import main.uslugiApacheCxf.services.GreetingServiceImpl;

import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class GreetingsCxf {

    @Autowired
    private CxfConfig cxfConfig;

    @Autowired
    GreetingCxfInterceptor greetingCxfInterceptor;

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(cxfConfig.springBus(), new GreetingServiceImpl());
        endpoint.publish("greetings");
        endpoint.getInInterceptors().add(greetingCxfInterceptor);
        return endpoint;
    }
}
