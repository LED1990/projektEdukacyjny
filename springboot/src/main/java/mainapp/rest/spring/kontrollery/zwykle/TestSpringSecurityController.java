package mainapp.rest.spring.kontrollery.zwykle;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/testSecurity")
public class TestSpringSecurityController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/stronaGlowna")
    public String przejdzNaStroneGlowna() {
        return "menu";
    }

    @GetMapping("/strona1")
    public String przejdzNaStrone1() {
        return "strona1";
    }

    @GetMapping("/strona2")
    public String przejdzNaStrone2() {
        return "strona2";
    }

    @GetMapping("/admin")
    public String przejdzNaStroneAdministratora() {
        return "admin";
    }

    /**
     * spring security powienien przevhwytywac niezalogowanych uzytkownikow
     */
    @GetMapping("/logowanie")
    public String przejdzNaStroneGlownaPoLogowaniu(){
        return "logowanie";
    }
}
