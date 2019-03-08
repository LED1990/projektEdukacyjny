package rest.cxf.coniguration;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.jaxrs.provider.JAXBElementProvider;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rest.cxf.serwisy.GreetingsServiceImpl;
import rest.cxf.serwisy.KomputerServiceImpl;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ApacheCxfRestConf {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Bus bus;

    @Autowired
    MessageInInterceptor messageInInterceptor;

    @Autowired
    MessageOutInterceptor messageOutInterceptor;

    @Autowired
    GreetingsServiceImpl greetingsServiceImpl;

    @Autowired
    KomputerServiceImpl komputerServiceImpl;

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
        endpoint.setServiceBeans(Arrays.asList(greetingsServiceImpl, komputerServiceImpl));
        endpoint.getInInterceptors().add(messageInInterceptor);
        endpoint.getOutInterceptors().add(messageOutInterceptor);
        endpoint.setExtensionMappings(przygotujExtensions());
        endpoint.setProviders(przygotujProviders());
        return endpoint.create();
    }

    /**
     * potrzebne dla apache cxf aby rozumial media typy podczas obsługi zapytań
     * mówimy mu co będzie obsługiwane
     * @return
     */
    private Map<Object, Object> przygotujExtensions(){
        Map<Object, Object> extensionMappings = new HashMap<>();
        extensionMappings.put("xml", MediaType.APPLICATION_XML);
        extensionMappings.put("json", MediaType.APPLICATION_JSON);
        return extensionMappings;
    }

    /**
     * potrzebne zeby cxf wiedział jak parsować odpowiedż na odpowiedni kontent
     * to robi prace - providery generuj body
     * @return
     */
    private List<Object> przygotujProviders(){
        List<Object> providers = new ArrayList<>();
        providers.add(new JAXBElementProvider());
        return providers;
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
