package mainapp.rest.spring.kontrollery.zwykle;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/testSecurity")
public class TestSpringSecurityController {

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
}
