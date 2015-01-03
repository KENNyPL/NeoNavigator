package pl.cydo.neo.navigator.web.controller.http;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String start(ModelMap model) {
        System.out.println("LoginController.printWelcome");

        return "login";
    }
}
