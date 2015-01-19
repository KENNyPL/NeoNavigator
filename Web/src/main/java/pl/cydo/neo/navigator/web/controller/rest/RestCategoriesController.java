package pl.cydo.neo.navigator.web.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.cydo.neo.navigator.business.service.TestService;
import pl.cydo.neo.navigator.model.map.service.category.ServicePointCategory;
import pl.cydo.neo.navigator.web.model.CategoriesTreeModel;

@RestController
@RequestMapping("/categories")
public class RestCategoriesController {
    @Autowired
    private TestService testService;


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
}
