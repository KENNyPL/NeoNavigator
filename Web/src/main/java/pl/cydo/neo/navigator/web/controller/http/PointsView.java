package pl.cydo.neo.navigator.web.controller.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.cydo.neo.navigator.business.service.ServicePointService;
import pl.cydo.neo.navigator.business.service.TestService;
import pl.cydo.neo.navigator.model.map.service.ServicePoint;
import pl.cydo.neo.navigator.model.map.service.category.ServicePointCategory;
import pl.cydo.neo.navigator.web.model.NewPoint;
import pl.cydo.neo.navigator.web.model.PointsTableData;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashSet;

@Controller
@RequestMapping("/points")
public class PointsView {

    @Autowired
    ServicePointService servicePointService;

    @Autowired
    TestService testService;

    @RequestMapping(method = RequestMethod.GET)
    public String root(final Model model) {
        model.addAttribute("newPoint", new NewPoint());
        return "points";
    }

    @ResponseBody
    @RequestMapping(value = "/{categoryName}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public PointsTableData byCategory(ModelMap model, @PathVariable String categoryName) {
        return new PointsTableData(servicePointService.find(categoryName, new PageRequest(0, 100)));
    }

    @RequestMapping(value = "/all/{categoryName}", method = RequestMethod.GET)
    public String getAllPoints(final Model model, @PathVariable String categoryName) {
        model.addAttribute("points", servicePointService.find(categoryName, new PageRequest(0, 100)));

        return "points";
    }

    @RequestMapping(value = "/add/{categoryName}", method = RequestMethod.GET)
    public String add(final Model model, @PathVariable String categoryName) {
        ServicePointCategory category = testService.findByName(categoryName);
        ServicePoint testPoint = new ServicePoint(new BigDecimal(new Date().getTime()), new BigDecimal(new Date().getTime()),
                new Date().toString(), new LinkedHashSet<ServicePointCategory>());
        testPoint.getCategories().add(category);
        servicePointService.create(testPoint);

        return getAllPoints(model, categoryName);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String add(@ModelAttribute("newPoint") NewPoint newPoint, Model model) {
        model.addAttribute("newPoint", newPoint);
        System.out.println(newPoint);
        servicePointService.create(new ServicePoint(newPoint.getLatitude(), newPoint.getLongitude(), newPoint.getName()), newPoint.getCategoryIds());

        return "points";
    }

    @RequestMapping(value = "/detail/{pointId}")
    public String detail(final Model model, @PathVariable String pointId) {
        model.addAttribute("point", servicePointService.find(pointId));

        return "point";
    }
}
