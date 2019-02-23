package main.uslugiApacheCxf.services;

import edukacyjne.services.greeting.GreetingRequest;
import edukacyjne.services.greeting.GreetingResponse;
import edukacyjne.services.greeting.Greetings;
import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements Greetings {

    @Override
    public GreetingResponse greet(GreetingRequest parameters) {
        GreetingResponse response = new GreetingResponse();
        if (parameters.getRequestMsg().equals("hejka cxf")){
            response.setKomunikat("odpowiedz dla cxf");
            response.setKod("cxf response");
        }else
        {
            response.setKomunikat("komunikat dla cxf defaultowy");
            response.setKod("kod dla cxf defaultowy");
        }
        return response;
    }
}
