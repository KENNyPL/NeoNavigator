package pl.cydo.neo.navigator.business.service;

import junit.framework.TestCase;
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
import pl.cydo.neo.navigator.business.repository.ServicePointCategoryRepository;
import pl.cydo.neo.navigator.business.repository.ZoneRepository;
import pl.cydo.neo.navigator.model.map.service.ServicePoint;
import pl.cydo.neo.navigator.model.map.service.category.ServicePointCategory;

import java.math.BigDecimal;
import java.util.HashSet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-graph-test.xml")
@Transactional
public class ServicePointServiceTest extends TestCase {
    @Before
    public void setUp() {
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
    private ServicePointCategoryRepository servicePointCategoryRepository;

    @Autowired
    private ServicePointService servicePointService;


    @Test
    public void shouldSaveAndFetch() {

        //given
        ServicePointCategory servicePointCategory = new ServicePointCategory("All");
        servicePointCategory = servicePointCategoryRepository.save(servicePointCategory);
        HashSet<ServicePointCategory> categories = new HashSet<ServicePointCategory>();
        categories.add(servicePointCategory);

        ServicePoint point1 = new ServicePoint(new BigDecimal(4000), new BigDecimal(14000), categories);
        ServicePoint point1_1 = new ServicePoint(new BigDecimal(4100), new BigDecimal(14400), categories);
        ServicePoint point1_2 = new ServicePoint(new BigDecimal(4200), new BigDecimal(14500), categories);
        ServicePoint point1_3 = new ServicePoint(new BigDecimal(4300), new BigDecimal(14600), categories);

        ServicePoint point1_4 = new ServicePoint(new BigDecimal(4300), new BigDecimal(22600), categories);

        ServicePoint point2 = new ServicePoint(new BigDecimal(5000), new BigDecimal(15000), categories);
        ServicePoint point3 = new ServicePoint(new BigDecimal(10000), new BigDecimal(18000), categories);
        ServicePoint point4 = new ServicePoint(new BigDecimal(70000), new BigDecimal(16000), categories);
        ServicePoint point5 = new ServicePoint(new BigDecimal(7000), new BigDecimal(61000), categories);


        //when
        servicePointService.create(point1);
        servicePointService.create(point1_1);
        servicePointService.create(point1_2);
        servicePointService.create(point1_3);

        servicePointService.create(point2);
        servicePointService.create(point3);
        servicePointService.create(point4);
        servicePointService.create(point5);

        //then

        assertEquals(servicePointService.find(8000L, 16000L, 2*9000L, servicePointCategory).size(), 6);
    }

}
