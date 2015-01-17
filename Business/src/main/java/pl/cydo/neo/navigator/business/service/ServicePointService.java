package pl.cydo.neo.navigator.business.service;

import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.cydo.neo.navigator.business.GeoMath;
import pl.cydo.neo.navigator.business.repository.PointRepository;
import pl.cydo.neo.navigator.business.repository.ServicePointCategoryRepository;
import pl.cydo.neo.navigator.business.repository.ZoneRepository;
import pl.cydo.neo.navigator.model.map.service.ServicePoint;
import pl.cydo.neo.navigator.model.map.service.category.ServicePointCategory;
import pl.cydo.neo.navigator.model.map.zone.Zone;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class ServicePointService {

    @Autowired
    private ZoneRepository zoneRepository;
    @Autowired
    private ServicePointCategoryRepository servicePointCategoryRepository;
    @Autowired
    private PointRepository pointRepository;

    @Transactional
    public void create(ServicePoint servicePoint) {
        Zone zone = null;
        long zoneLat = (servicePoint.getLatitude().longValue() / GeoMath.GPS_POSITION_1KM_DISTANCE) * GeoMath.GPS_POSITION_1KM_DISTANCE;
        long zoneLong = (servicePoint.getLongitude().longValue() / GeoMath.GPS_POSITION_1KM_DISTANCE) * GeoMath.GPS_POSITION_1KM_DISTANCE;
        Iterable<Zone> iterator = zoneRepository.findByLatitudeAndLongitude(zoneLat, zoneLong);

        //todo: jcygan: check if  iterator.iterator().hasNext() sometimes throw NullPointerException
        try {
            if (iterator != null && iterator.iterator() != null && iterator.iterator().hasNext()) {
                zone = iterator.iterator().next();
            } else {
                zone = new Zone(zoneLat, zoneLong);
            }
        } catch (Exception e) {
            e.printStackTrace();
            zone = new Zone(zoneLat, zoneLong);
        }
        zone.addPoint(servicePoint);
        zone = zoneRepository.save(zone);

        pointRepository.save(servicePoint);

    }

    @Transactional
    public void create(ServicePoint servicePoint, List<Long> categoryIds) {
        for (Long categoryId : categoryIds) {
            servicePoint.getCategories().add(servicePointCategoryRepository.findOne(categoryId));
        }
        create(servicePoint);
    }

    public Collection<ServicePoint> find(String categoryName, Pageable pageable) {
        return IteratorUtils.toList(pointRepository.findByCategoryName(categoryName, pageable).iterator());
    }

    @Transactional
    public List<ServicePoint> find(Long latitude, Long longitude, Long distance, String categoryName) {
        List<ServicePoint> result = new LinkedList<ServicePoint>();
        List<Zone> zones = fetchZones(latitude, longitude, distance);
        ServicePointCategory category = servicePointCategoryRepository.findByName(categoryName);

        for (Zone zone : zones) {
            result.addAll(IteratorUtils.toList(pointRepository.getAllZonesPoints(zone, category).iterator()));
        }
        return result;
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

        long latFrom = latitude - (distance);
        long latTo = latitude + (distance);
        long longFrom = longitude - (distance);
        long longTo = longitude + (distance);

        long zoneFromLat = ((latFrom / zoneSize) * zoneSize);
        long zoneToLat = ((latTo / zoneSize) * zoneSize) + zoneSize;
        long zoneFromLong = ((longFrom / zoneSize) * zoneSize);
        long zoneToLong = ((longTo / zoneSize) * zoneSize) + zoneSize;

        for (long x = zoneFromLong; x < zoneToLong; x += zoneSize) {
            for (long y = zoneFromLat; y < zoneToLat; y += zoneSize) {
                if (zoneRepository.findByLatitudeAndLongitude(y, x).iterator().hasNext()) {
                    zones.add(zoneRepository.findByLatitudeAndLongitude(y, x).iterator().next());
                }
            }
        }

        return zones;
    }

    public ServicePoint find(String pointId) {
        return pointRepository.findOne(Long.parseLong(pointId));
    }

    public Long getCount() {
        return pointRepository.count();
    }

    public List<ServicePoint> findAll() {
        return pointRepository.findAll().as(List.class);
    }
}
