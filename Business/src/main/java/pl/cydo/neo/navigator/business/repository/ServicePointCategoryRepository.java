package pl.cydo.neo.navigator.business.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Component;
import pl.cydo.neo.navigator.model.map.service.category.ServicePointCategory;

public interface ServicePointCategoryRepository extends GraphRepository<ServicePointCategory> {
    ServicePointCategory findByName(String name);
}
