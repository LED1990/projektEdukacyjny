package mainapp.rest.spring.conf;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTempConf {

    @Bean
    RestTemplate budujRestTemplate(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.build();
    }
}
