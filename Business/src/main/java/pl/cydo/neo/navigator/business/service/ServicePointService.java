package pl.cydo.neo.navigator.business.service;

import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.cydo.neo.navigator.business.GeoMath;
import pl.cydo.neo.navigator.business.repository.PointRepository;
import pl.cydo.neo.navigator.business.repository.ServicePointCategoryRepository;
import pl.cydo.neo.navigator.business.repository.ServicePointRepository;
import pl.cydo.neo.navigator.business.repository.ZoneRepository;
import pl.cydo.neo.navigator.model.map.service.ServicePoint;
import pl.cydo.neo.navigator.model.map.service.category.ServicePointCategory;
import pl.cydo.neo.navigator.model.map.zone.Zone;

import java.util.LinkedList;
import java.util.List;

@Service
public class ServicePointService {

    @Autowired
    private ZoneRepository zoneRepository;
    @Autowired
    private ServicePointCategoryRepository servicePointCategoryRepository;
    @Autowired
    private ServicePointRepository servicePointRepository;

    @Autowired
    private PointRepository pointRepository;

    @Transactional
    public void create(ServicePoint servicePoint) {
        Zone zone = null;
        long zoneLat=(servicePoint.getLatitude().longValue()/GeoMath.GPS_POSITION_1KM_DISTANCE)*GeoMath.GPS_POSITION_1KM_DISTANCE;
        long zoneLong=(servicePoint.getLongitude().longValue()/GeoMath.GPS_POSITION_1KM_DISTANCE)*GeoMath.GPS_POSITION_1KM_DISTANCE;
        Iterable<Zone> iterator = zoneRepository.findByLatitudeAndLongitude(zoneLat, zoneLong);
        if (iterator.iterator().hasNext()) {
            zone = iterator.iterator().next();
        } else {
            zone = new Zone(zoneLat, zoneLong);
        }
        zone.addPoint(servicePoint);
        zone= zoneRepository.save(zone);

        servicePointRepository.save(servicePoint);

    }

    public List<ServicePoint> find(Long latitude, Long longitude, Long distance, ServicePointCategory category) {
        List<ServicePoint> result = new LinkedList<ServicePoint>();
        List<Zone> zones = fetchZones(latitude, longitude, distance);
        for (Zone zone : zones) {
            result.addAll(IteratorUtils.toList(pointRepository.getAllZonesPoints(zone, category).iterator()));
        }
        return result;
    }

    private List<Zone> fetchZones(long latitude, long longitude, long distance) {
        List<Zone> zones = new LinkedList<Zone>();
        long zoneSize = GeoMath.GPS_POSITION_1KM_DISTANCE;

        long latFrom = latitude - (distance / 2);
        long latTo = latitude + (distance / 2);
        long longFrom = longitude - (distance / 2);
        long longTo = longitude + (distance / 2);

        long zoneFromLat = ((latFrom / zoneSize) * zoneSize);
        long zoneToLat = ((latTo / zoneSize) * zoneSize)+zoneSize;
        long zoneFromLong = ((longFrom / zoneSize) * zoneSize);
        long zoneToLong = ((longTo / zoneSize) * zoneSize)+zoneSize;

        for (long x = zoneFromLong; x < zoneToLong; x += zoneSize) {
            for (long y = zoneFromLat; y < zoneToLat; y += zoneSize) {
                if (zoneRepository.findByLatitudeAndLongitude(y, x).iterator().hasNext()) {
                    zones.add(zoneRepository.findByLatitudeAndLongitude(y, x).iterator().next());
                }
            }
        }

        return zones;
    }

}
