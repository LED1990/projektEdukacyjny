package main.endpoints;

import edukacyjne.services.greeting.GreetingRequest;
import edukacyjne.services.greeting.GreetingResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class Greetings {

    @PayloadRoot(namespace = "http://www.edukacyjne/services/greeting/", localPart = "GreetingRequest")
    @ResponsePayload
    public GreetingResponse getGreetings(@RequestPayload GreetingRequest request){
        GreetingResponse response = new GreetingResponse();
        if (request.getRequestMsg().contains("hejka")){
            response.setKod("ODP");
            response.setKomunikat("witam Cię jako usługa soap");
        }else {
            response.setKod("domyślny kod");
            response.setKomunikat("domyślna odpowiedź");
        }
        return response;
    }
}
