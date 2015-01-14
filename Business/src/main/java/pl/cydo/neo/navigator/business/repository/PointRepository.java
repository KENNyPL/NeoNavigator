package pl.cydo.neo.navigator.business.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Component;
import pl.cydo.neo.navigator.model.map.service.ServicePoint;
import pl.cydo.neo.navigator.model.map.service.category.ServicePointCategory;
import pl.cydo.neo.navigator.model.map.zone.Zone;

import java.util.Collection;
import java.util.List;

@Component
public interface PointRepository extends GraphRepository<ServicePoint> {
    @Query("start zone=node({0}) match zone-[zp:ZONE_POINT]->(point:ServicePoint)<-[c:CATEGORY] - (cat:ServicePointCategory) " +
            "where cat.name in {1} return point")
    Iterable<ServicePoint> getAllZonesPoints(Zone zone, String categoryName);

    @Query("start zone=node({0}) match zone-[zp:ZONE_POINT]->(point:ServicePoint)<-[c:CATEGORY] - (cat:ServicePointCategory) " +
            " where id(cat)={1} return point")
    Iterable<ServicePoint> getAllZonesPoints(Zone zone, ServicePointCategory category);

//    @Query("match (p:Point) <- [c:CATEGORY_POINTS] - (cat:ServicePointCategory), " +
//            "(p2:Point) <- [cp:CATEGORY_POINTS] - (subCat:ServicePointCategory) - [sc: SUB_CATEGORY*0..] - " +
//            "(cat2:ServicePointCategory)" +
//            "where cat.name = {0} and cat2.name={0}  return p, p2")

    @Query("match (cat:ServicePointCategory) - [sc: SUB_CATEGORY*0..] -> (subcat:ServicePointCategory) - " +
            " [cp2:CATEGORY_POINTS] - (p2:Point) "+
            " where cat.name = {0}  return p2")

//    @Query("match (p:Point) <- [c:CATEGORY_POINTS] - (cat:ServicePointCategory) " +
//                  " - [sc: SUB_CATEGORY*0..] - (subcat:ServicePointCategory)" +
//                  "where cat.name = {0} or subcat.name={0} return DISTINCT p")
//    @Query("match (point) <- [c:CATEGORY_POINTS] - (cat:ServicePointCategory) - " +
//            "[sc: SUB_CATEGORY*0..] - (subcat:ServicePointCategory) - " +
//            "[c2:CATEGORY_POINTS] -> (point)" +
//            "where cat.name = {0} return point")
    List<ServicePoint> findByCategoryName(String categoryName, Pageable pageable);
}
