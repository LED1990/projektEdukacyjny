package rest.cxf.coniguration;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rest.cxf.serwisy.GreetingsServiceImpl;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class ApacheCxfRestConf {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Bus bus;

    @Autowired
    GreetingsServiceImpl greetingsServiceImpl;

    /**
     * konfigracj serwera (endpointa) dla usług apache cxf
     * @return
     */
    @Bean
    public Server restServer(){
        logger.debug("-------------------------- konfiguracja endpointa");
        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setBus(bus);
        endpoint.setAddress("/");
        endpoint.setServiceBeans(Collections.singletonList(greetingsServiceImpl));
        endpoint.getInInterceptors().add(new MessageInInterceptor());
        endpoint.getOutInterceptors().add(new MessageOutInterceptor());
        return endpoint.create();
    }

    /**
     * korzystając ze springboota i apache cxf po dodadniu dependency cxf-spring-boot-starter-jaxrs springboot automatycznie zaresjestruje servlet
     * dla apache cxf.
     * Zeby jednak zminić mapowanie servletu z domyślnego ("services/*) należy ręcznie zarejestrowac servlet apache cxf
     * przykład poniżej
     * można też w ten sposób dokonac pozostałej konfiguracji servletu cxf
     * @return
     */
    @Bean
    public ServletRegistrationBean cxfServlet(){
        logger.debug("-------------------------- konfiguracja servletu cxf");
        final ServletRegistrationBean servletRegistrationBean =  new ServletRegistrationBean(new CXFServlet(), "/cxfspring/*");
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }

}
