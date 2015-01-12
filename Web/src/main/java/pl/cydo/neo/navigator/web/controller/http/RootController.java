package pl.cydo.neo.navigator.web.controller.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.cydo.neo.navigator.business.service.ServicePointService;
import pl.cydo.neo.navigator.business.service.TestService;
import pl.cydo.neo.navigator.model.map.service.category.ServicePointCategory;

@Controller()
public class RootController {
    @Autowired
    private ServicePointService pointService;

    @Autowired
    private TestService testService;

    @RequestMapping( value = "/", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("point_count", pointService.getCount());
        model.addAttribute("category_count", testService.getCount());
        model.addAttribute("zone_count", 0);
        model.addAttribute("user_count", 0);
        return "index";
    }

    //Spring Security see this :
    @RequestMapping(value = "/login_user", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {


        System.out.println("RootController.login");
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;
    }
}
