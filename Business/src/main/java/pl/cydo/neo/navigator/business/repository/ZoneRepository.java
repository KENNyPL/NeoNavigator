package pl.cydo.neo.navigator.business.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import pl.cydo.neo.navigator.model.map.zone.Zone;

public interface ZoneRepository extends GraphRepository<Zone> {

}
