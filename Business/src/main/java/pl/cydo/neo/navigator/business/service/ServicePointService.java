package pl.cydo.neo.navigator.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.cydo.neo.navigator.business.repository.ServicePointCategoryRepository;
import pl.cydo.neo.navigator.business.repository.ZoneRepository;
import pl.cydo.neo.navigator.model.map.service.ServicePoint;

@Service
public class ServicePointService {

    @Autowired
    private ZoneRepository zoneRepository;
    @Autowired
    private ServicePointCategoryRepository servicePointCategoryRepository;

    public void create(ServicePoint servicePoint){

    }

}
