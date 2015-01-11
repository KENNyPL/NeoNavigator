package pl.cydo.neo.navigator.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.cydo.neo.navigator.business.repository.ServicePointCategoryRepository;
import pl.cydo.neo.navigator.model.map.service.category.ServicePointCategory;

@Service
public class TestService {

    @Autowired
    ServicePointCategoryRepository servicePointCategoryRepository;

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
}
