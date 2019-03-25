package mainapp.rest.cxf.coniguration;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import mainapp.rest.cxf.exceptions.MWebApplicationException;
import mainapp.rest.cxf.serwisy.GreetingsServiceImpl;
import mainapp.rest.cxf.serwisy.KomputerServiceEtagImpl;
import mainapp.rest.cxf.serwisy.KomputerServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.provider.JAXBElementProvider;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ApacheCxfRestConf {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final String BASE_PATH = "cxfspring";

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

    @Autowired
    MWebApplicationException mWebApplicationException;

    @Autowired
    KomputerServiceEtagImpl komputerServiceEtagImpl;

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
        endpoint.setServiceBeans(Arrays.asList(greetingsServiceImpl, komputerServiceImpl, komputerServiceEtagImpl));
        endpoint.getInInterceptors().add(messageInInterceptor);
        endpoint.getOutInterceptors().add(messageOutInterceptor);
        endpoint.setExtensionMappings(przygotujExtensions());
        endpoint.setProviders(przygotujProviders());
        endpoint.setFeatures(Collections.singletonList(swagger2Feature()));
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
        providers.add(new JacksonJsonProvider());
        providers.add(new JAXBElementProvider());
        providers.add(mWebApplicationException);
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
        final ServletRegistrationBean<CXFServlet> servletRegistrationBean =  new ServletRegistrationBean<>(new CXFServlet(), "/" + BASE_PATH + "/*");
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }

    /**
     * konfiguracja swaggera do opisania aplikacji
     * dostęp pod adresem: http://localhost:9081/cxfspring/api-docs?url=/cxfspring/swagger.json
     * @return
     */
    @Bean("swagger2Feature")
    public Feature swagger2Feature(){
        Swagger2Feature swagger2Feature = new Swagger2Feature();
        swagger2Feature.setTitle("swager rest apache cxf tutorial");
        swagger2Feature.setDescription("testuje działnie swaggera ze springiem bootem i cxf dla usług restowych");
        swagger2Feature.setBasePath(BASE_PATH);
        swagger2Feature.setVersion("v1");
        swagger2Feature.setSchemes(new String[] { "http", "https" });
        swagger2Feature.setPrettyPrint(true);
        return swagger2Feature;
    }

    /**
     * korzystajac z ShallowEtagHeaderFilter nie będzie trzeba samemu zajmować się obsługa etagów!!
     * ich wartość będzie generowana automatycznie przez springa.
     * bez tego samemu by trzeba generować tagi, dodawać je do headera itp.
     * @return
     */
    @Bean
    public FilterRegistrationBean<ShallowEtagHeaderFilter> shallowEtagHeaderFilterFilterRegistrationBean(){
        FilterRegistrationBean<ShallowEtagHeaderFilter> filterFilterRegistrationBean  = new FilterRegistrationBean<>(new ShallowEtagHeaderFilter());
        filterFilterRegistrationBean.addUrlPatterns("/" + BASE_PATH +"/v1/komp/etag/*");
        filterFilterRegistrationBean.setName("FiltrZapytanEtag");
        return filterFilterRegistrationBean;
    }

}
