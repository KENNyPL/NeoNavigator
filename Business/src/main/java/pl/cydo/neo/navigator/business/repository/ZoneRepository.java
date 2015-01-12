package pl.cydo.neo.navigator.business.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.cydo.neo.navigator.model.map.zone.Zone;

@Component
public interface ZoneRepository extends GraphRepository<Zone> {

    @Transactional
    Iterable<Zone> findByLatitudeAndLongitude(Long latitude, Long longitude);
}
