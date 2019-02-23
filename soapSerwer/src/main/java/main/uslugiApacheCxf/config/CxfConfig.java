package main.uslugiApacheCxf.config;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfConfig {

    /**
     * rejestrowanie  servletu apache cxf zeby można było obsługiwać zapytania soap przy  pomocy apache cxf
     * !!!jeśli używam dependencie niżej
     * cxf-spring-boot-starter-jaxws <----------------
     * dla springboota nie muszę rejestrować tego servletu!!!
     * spring boot robi to za mnie
     * ważna jest '*' podczas rejestracji servletu!!!! pozwala ona obłsugiwać wszystkie mapowania (URLe)
     * @return
     */
    @Bean
    public ServletRegistrationBean<CXFServlet> dispacherServletCxf(){
        return new ServletRegistrationBean<>(new CXFServlet(), "/ws-cxf/*");
    }

    /**
     * pozwala  na współ pracę apache cxf  ze springiem
     *
     * @return
     */
    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

}
