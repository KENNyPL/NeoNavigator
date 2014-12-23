package pl.cydo.neo.navigator.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.cydo.neo.navigator.business.service.ServicePointService;
import pl.cydo.neo.navigator.model.map.service.ServicePoint;
import pl.cydo.neo.navigator.model.map.service.category.ServicePointCategory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashSet;

@Controller
@RequestMapping("/points")
public class PointsView {

    @Autowired
    ServicePointService servicePointService;

    @RequestMapping(value = "/all/{categoryName}", method = RequestMethod.GET)
    public String getAllPoints(final Model model, @PathVariable String categoryName) {
        model.addAttribute("points", servicePointService.find(categoryName, new PageRequest(0, 100)));

        return "points";
    }

    @RequestMapping(value = "/add/{categoryName}", method = RequestMethod.GET)
    public String add(final Model model, @PathVariable String categoryName) {
        ServicePoint testPoint = new ServicePoint(new BigDecimal(new Date().getTime()), new BigDecimal(new Date().getTime()),
                new Date().toString(), new LinkedHashSet<ServicePointCategory>());
        testPoint.getCategories().add(new ServicePointCategory(categoryName));
        servicePointService.create(testPoint);

        return getAllPoints(model, categoryName);
    }

    @RequestMapping(value = "/detail/{pointId}")
    public String detail(final Model model, @PathVariable String pointId) {
        model.addAttribute("point", servicePointService.find(pointId));

        return "point";
    }
}
