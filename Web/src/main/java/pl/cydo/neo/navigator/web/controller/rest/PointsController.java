package pl.cydo.neo.navigator.web.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.cydo.neo.navigator.business.service.ServicePointService;
import pl.cydo.neo.navigator.model.map.service.ServicePoint;

import java.util.List;

@RestController
@RequestMapping("/points")
public class PointsController {

    @Autowired
    private ServicePointService pointService;

    @RequestMapping(value = "/all/{longitude}/{latitude}/{distance}/{categoryName}")
    public List<ServicePoint> getPointsByPositionDistanceAndCategoryName(@PathVariable Long longitude,
                                                                         @PathVariable Long latitude,
                                                                         @PathVariable Long distance,
                                                                         @PathVariable String categoryName) {
        return pointService.find(latitude, longitude, distance, categoryName);
    }
}
