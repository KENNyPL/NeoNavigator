package pl.cydo.neo.navigator.web.controller.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.cydo.neo.navigator.business.service.TestService;
import pl.cydo.neo.navigator.model.map.service.category.ServicePointCategory;
import pl.cydo.neo.navigator.web.model.CategoriesTreeModel;

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
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public CategoriesTreeModel fetCategoriesTree(ModelMap model) {

        CategoriesTreeModel root = new CategoriesTreeModel();
        root.setText("Root");

        for (ServicePointCategory category:testService.findAll()) {
            root.add(CategoriesTreeModel.from(category));
        }

        return root;
    }
}
