package mainapp.rest.kontrolery.restowe;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * adnotacja @Restcontroller zwaraca obiekt do ciała odpowiedzi zamiast nazwy widoku jak to jest w zwykłych kontrolerach (@Controller)
 * taki sam efekt sie uzyskuje łącząc adnotację @Controller z @ResoponseBody
 */

@RestController
@RequestMapping("/restowe")
public class GreetingsController {

    @RequestMapping("/kontrolerRest")
    public String powitanieKontrolerResowy(){
        return "witaj z kontrolera resowego na spring bootcie";
    }
}
