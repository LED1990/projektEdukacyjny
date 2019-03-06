package rest.cxf.coniguration;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rest.cxf.serwisy.GreetingsServiceImpl;

import java.util.Arrays;

@Configuration
public class ApacheCxfRestConf {

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
        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setBus(bus);
        endpoint.setAddress("/");
        endpoint.setServiceBeans(Arrays.asList(greetingsServiceImpl));
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
        final ServletRegistrationBean servletRegistrationBean =  new ServletRegistrationBean(new CXFServlet(), "/cxfspring/*");
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }

}
