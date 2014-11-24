package pl.cydo.neo.navigator.web.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.cydo.neo.navigator.business.service.TestService;
import pl.cydo.neo.navigator.model.map.service.category.ServicePointCategory;

@RestController
public class SimpleController {
    @Autowired
    private TestService service;

    public TestService getService() {
        return service;
    }

    public void setService(TestService service) {
        this.service = service;
    }

    @RequestMapping("/get")
    public String get() {
        System.out.println("SimpleController.get");
        return "hello";
    }
    @RequestMapping("/add")
    public String add(@RequestParam(value = "name", defaultValue = "Test") String name) {
        System.out.println("SimpleController.get");
        ServicePointCategory newPointCategory = new ServicePointCategory();
        newPointCategory.setName(name);
        service.save(newPointCategory);
        return "Added";
    }

    @RequestMapping("/find")
    public ServicePointCategory add(@RequestParam(value = "id", defaultValue = "1") Long id) {
        System.out.println("SimpleController.get");
        ServicePointCategory result = service.findOne(id);
        return result;
    }

    @ResponseBody
    @RequestMapping("/get2")
    public String get2() {
        return "hello";
    }

}
