package pl.cydo.neo.navigator.business.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.cydo.neo.navigator.business.GeoMath;
import pl.cydo.neo.navigator.model.map.point.Point;
import pl.cydo.neo.navigator.model.map.service.ServicePoint;
import pl.cydo.neo.navigator.model.map.service.category.ServicePointCategory;
import pl.cydo.neo.navigator.model.map.zone.Zone;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-graph-test.xml")
@Transactional
public class ZoneRepositoryTest {

    long beginLong = 50950000;
    long beginLat = 20500000;
    long endLong = 50700000;
    long endLat = 20750000;

    private List<Zone> zones;

    @Before
    public void setUp() {
        zones = getZones(beginLong, beginLat, endLong, endLat);
    }

    @Bean
    GraphDatabaseService graphDatabaseService() {
        return new GraphDatabaseFactory().newEmbeddedDatabase("accessingdataneo4j.db");
    }

    @Bean
    public Neo4jTemplate neo4jTemplate() {
        return new Neo4jTemplate(graphDatabaseService());
    }

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private PointRepository pointRepository;
    @Autowired
    private ServicePointCategoryRepository servicePointCategoryRepository;

    @Test
    public void shouldFetchAllPointsUsingCategoryEntity() {
        //given
        ServicePointCategory categoryFromDb = null;
        HashSet<ServicePointCategory> categories = new HashSet();
        categories.add(new ServicePointCategory("Test"));
        //todo: check if after save is different test result
        ((ServicePoint) zones.get(0).getPoints().iterator().next()).setCategories(categories);

        //when
        zoneRepository.save(zones);
        categoryFromDb = servicePointCategoryRepository.findByName("Test");
        Zone testZone = zoneRepository.findOne(zones.get(0).getId());


        //then
        assertEquals(true, pointRepository.getAllZonesPoints(testZone, categoryFromDb).iterator().hasNext());
    }

    @Test
    public void shouldFetchAllPoints() {
        //given
        HashSet<ServicePointCategory> categories = new HashSet();
        categories.add(new ServicePointCategory("Test"));
        //todo: check if after save is different test result
        ((ServicePoint) zones.get(0).getPoints().iterator().next()).setCategories(categories);

//when
        zoneRepository.save(zones);
        Zone testZone = zoneRepository.findOne(zones.get(0).getId());
        //then
        assertEquals(pointRepository.getAllZonesPoints(testZone, "").iterator().hasNext(), false);
        assertEquals(pointRepository.getAllZonesPoints(testZone, "Test").iterator().hasNext(), true);
    }

    @Test
    public void shouldProperlyAddAndFetchZonePints() {
        //given
        Zone testZone = zones.get(0);
        Zone testZoneFromDb = null;
        Point zonePoint1 = new ServicePoint(new BigDecimal(11), new BigDecimal(11), null);
        Point zonePoint2 = new ServicePoint(new BigDecimal(22), new BigDecimal(2), null);

        //when
        testZone.getPoints().add(zonePoint1);
        testZone = zoneRepository.save(testZone);
        testZoneFromDb = zoneRepository.findOne(testZone.getId());
        testZoneFromDb.getPoints().add(zonePoint2);
        zoneRepository.save(testZoneFromDb);
        testZone = zoneRepository.findOne(testZoneFromDb.getId());

        //then
        assertEquals(testZone.getPoints().size(), 3);
    }

    @Test
    public void shouldFindAll() {
        //given
        int count = 0;
        Iterator<Zone> iterator = zoneRepository.findAll().iterator();
        Zone firstZone = null;

        //when
        zoneRepository.save(zones);

        while (iterator.hasNext()) {
            if (firstZone == null) {
                firstZone = iterator.next();
            } else {
                System.out.println(iterator.next());
            }
            count++;
        }

        //then
        assertEquals(count, 28 * 28);
        assertEquals(firstZone.getPoints().size(), 1);
        assertEquals(firstZone.getPoints().iterator().next().getLatitude().longValue(), firstZone.getLatitude().longValue() - GeoMath.GPS_POSITION_1KM_DISTANCE / 2);
        assertEquals(firstZone.getPoints().iterator().next().getLongitude().longValue(), firstZone.getLongitude().longValue() + GeoMath.GPS_POSITION_1KM_DISTANCE / 2);
    }

    private List<Zone> getZones(long beginLong, long beginLat, long endLong, long endLat) {
        //punkty dla kielc:
        List<Zone> zones = new LinkedList();
        Zone[][] zoneWeb = new Zone[(int) ((beginLong - endLong) / GeoMath.GPS_POSITION_1KM_DISTANCE) + 1]
                [(int) ((endLat - beginLat) / GeoMath.GPS_POSITION_1KM_DISTANCE) + 1];

        int x = 0;
        int y = 0;
        for (long i = beginLong; i > endLong; i = i - GeoMath.GPS_POSITION_1KM_DISTANCE) {
            x = 0;
            for (long j = beginLat; j < endLat; j = j + GeoMath.GPS_POSITION_1KM_DISTANCE) {
                Zone zoneNode = new Zone(j, i);
                zoneNode.getPoints().add(new ServicePoint(
                        new BigDecimal(j - GeoMath.GPS_POSITION_1KM_DISTANCE / 2),
                        new BigDecimal(i + GeoMath.GPS_POSITION_1KM_DISTANCE / 2),
                        null));

                zones.add(zoneNode);
                zoneWeb[y][x] = zoneNode;

                x++;
            }
            y++;
        }
        return zones;
    }
}
