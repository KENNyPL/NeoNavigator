package pl.cydo.neo.navigator.business.repository;

import org.junit.Assert;
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
import pl.cydo.neo.navigator.business.*;
import pl.cydo.neo.navigator.business.Math;
import pl.cydo.neo.navigator.model.map.zone.Zone;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-graph-test.xml")
@Transactional
public class ZoneRepositoryTest {
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

    @Test
    public void shouldFindAll() {

        //given: punkty dla kielc:
        long beginLong = 50950000;
        long beginLat = 20500000;
        long endLong = 50700000;
        long endLat = 20750000;

        List<Zone> zones = new LinkedList();
        Zone[][] zoneWeb = new Zone[(int) ((beginLong - endLong) / Math.GPS_POSITION_1KM_DISTANCE) + 1]
                [(int) ((endLat - beginLat) / Math.GPS_POSITION_1KM_DISTANCE) + 1];

        int x = 0;
        int y = 0;
        for (long i = beginLong; i > endLong; i = i - Math.GPS_POSITION_1KM_DISTANCE) {
            x = 0;
            for (long j = beginLat; j < endLat; j = j + Math.GPS_POSITION_1KM_DISTANCE) {
                Zone zoneNode = new Zone(i, j);

                zones.add(zoneNode);
                zoneWeb[y][x] = zoneNode;

                x++;
            }
            y++;
        }
        //when
        zoneRepository.save(zones);

        setZonesNeighbors(zoneWeb);


        int count = 0;
        Iterator<Zone> iterator = zoneRepository.findAll().iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            count++;
        }

        //then
        Assert.assertEquals(count, 28 * 28);
        Assert.assertEquals(zoneWeb[10][10].getNorthNeighbor().getLongitude(),
                new Long(zoneWeb[10][10].getLongitude().longValue() + Math.GPS_POSITION_1KM_DISTANCE));
        Assert.assertEquals(zoneWeb[10][10].getEastNeighbor().getLatitude(),
                new Long(zoneWeb[10][10].getLatitude().longValue() + Math.GPS_POSITION_1KM_DISTANCE));


    }

    private void setZonesNeighbors(Zone[][] zoneWeb) {
        for (int y = 0; y < 28; y++) {
            for (int x = 0; x < 28; x++) {
                try {
                    zoneWeb[y][x].setNorthNeighbor(zoneWeb[y - 1][x]);
                } catch (Exception e) {
//                    System.out.println(e.getMessage());
                }
                try {
                    zoneWeb[y][x].setSouthNeighbor(zoneWeb[y + 1][x]);
                } catch (Exception e) {
//                    System.out.println(e.getMessage());
                }
                try {
                    zoneWeb[y][x].setWestNeighbor(zoneWeb[y][x - 1]);
                } catch (Exception e) {
//                    System.out.println(e.getMessage());
                }
                try {
                    zoneWeb[y][x].setEastNeighbor(zoneWeb[y][x + 1]);
                } catch (Exception e) {
//                    System.out.println(e.getMessage());
                }
                zoneRepository.save(zoneWeb[y][x]);

//                System.out.println("SET NEIGHBOR: " + zoneWeb[y][x]);
            }
        }

    }
}
