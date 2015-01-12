package pl.cydo.neo.navigator.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.cydo.neo.navigator.business.repository.ServicePointCategoryRepository;
import pl.cydo.neo.navigator.business.repository.ZoneRepository;
import pl.cydo.neo.navigator.model.map.service.category.ServicePointCategory;
import scala.collection.mutable.HashSet;

import java.util.Set;

@Service
@Transactional
public class TestService {

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private ServicePointCategoryRepository servicePointCategoryRepository;

    @Autowired
    private ServicePointService servicePointService;

    public TestService() {
    }

    public <S extends ServicePointCategory> S save(S entity) {
        return servicePointCategoryRepository.save(entity);
    }

    public ServicePointCategory findOne(Long aLong) {
        return servicePointCategoryRepository.findOne(aLong);
    }

    public ServicePointCategory findByName(String name) {
        return servicePointCategoryRepository.findByName(name);
    }

    public Long getCount() {
        return servicePointCategoryRepository.count();
    }

    @Transactional
    public Set<ServicePointCategory> findAll() {
        return servicePointCategoryRepository.findAll().as(Set.class);
    }
}
