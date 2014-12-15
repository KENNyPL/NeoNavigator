package pl.cydo.neo.navigator.business.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import pl.cydo.neo.navigator.model.map.service.ServicePoint;

public interface ServicePointRepository extends GraphRepository<ServicePoint> {
}
