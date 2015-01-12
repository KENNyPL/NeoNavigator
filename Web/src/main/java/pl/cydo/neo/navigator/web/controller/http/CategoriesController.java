package pl.cydo.neo.navigator.web.controller.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.cydo.neo.navigator.business.service.TestService;
import pl.cydo.neo.navigator.model.map.service.category.ServicePointCategory;
import pl.cydo.neo.navigator.web.model.CategoriesTreeModel;
import pl.cydo.neo.navigator.web.model.OperationResponse;

import javax.ws.rs.PathParam;

@Controller
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    private TestService testService;


    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        return "categories";
    }


    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public CategoriesTreeModel fetCategoriesTree(ModelMap model) {

        ServicePointCategory rootServicePointCategory = testService.findByName("root");
        if (rootServicePointCategory == null) {
            rootServicePointCategory = new ServicePointCategory("root");
            rootServicePointCategory = testService.save(rootServicePointCategory);
        }
        CategoriesTreeModel root = CategoriesTreeModel.from(rootServicePointCategory);

        return root;
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public OperationResponse add(ModelMap model, @RequestParam Long id, @RequestParam String name) {
        System.out.println(id + "---" + name);

        testService.addSubCategory(id, name);


        if (name.equals("bad")) {
            throw new NullPointerException();
        }
        return new OperationResponse("OK", "");
    }

    @ResponseBody
    @RequestMapping(value = "/rename", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public OperationResponse rename(ModelMap model, @RequestParam Long id, @RequestParam String name) {

        testService.rename(id, name);


        return new OperationResponse("OK", "");
    }
    @ResponseBody
    @RequestMapping(value = "/byId/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public OperationResponse delete(@PathVariable(value = "id") Long id) {
        testService.delete(id);

        return new OperationResponse("OK", "");
    }

}
