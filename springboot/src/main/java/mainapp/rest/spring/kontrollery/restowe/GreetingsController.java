package mainapp.rest.spring.kontrollery.restowe;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * adnotacja @Restcontroller zwaraca obiekt do ciała odpowiedzi zamiast nazwy widoku jak to jest w zwykłych kontrolerach (@Controller) - pomijany jest view resolwer
 * taki sam efekt sie uzyskuje łącząc adnotację @Controller z @ResoponseBody pod @RequestMapping
 */

@RestController
@RequestMapping("/restowe")
public class GreetingsController {

    /**
     * to mapowanie nie jest bronione przez spring security
     * @return
     */
    @RequestMapping("/kontrolerRest")
    public String powitanieKontrolerResowy(){
        return "witaj z kontrolera resowego na spring bootcie";
    }

    @RequestMapping("/dlaAdmina")
    public String powitanieAdmina(){
        return "witaj adminie usługi restowej";
    }

    @RequestMapping("/dlaZalogowanych")
    public String powitanieZalogowanych(){
        return "witajcie użytkownicy widziani przez spring security!";
    }
}
