package pl.cydo.neo.navigator.business.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import pl.cydo.neo.navigator.business.repository.ServicePointCategoryRepository;
import pl.cydo.neo.navigator.business.repository.ZoneRepository;
import pl.cydo.neo.navigator.model.map.service.ServicePoint;
import pl.cydo.neo.navigator.model.map.service.category.ServicePointCategory;

import java.math.BigDecimal;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-graph-test.xml")
@Transactional
public class TestServiceTest {
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
    @Autowired
    private TestService testService;

    @Test
    public void shouldNoDeleteAnyCategory() {
        ServicePointCategory root = new ServicePointCategory("root");
        ServicePointCategory child1 = new ServicePointCategory("c1");
        ServicePointCategory child2 = new ServicePointCategory("c2");
        ServicePointCategory child3 = new ServicePointCategory("c3");
        root.add(child1);
        root.add(child2);
        root.add(child3);
        child3.getPoints().add(new ServicePoint());


        root = servicePointCategoryRepository.save(root);
        Long rootId=root.getId();

        assertEquals(root.getSubCategories().size(), 3);
        try {
            testService.delete(root.getId());
        }catch (Exception e){
            // do nothing
            e.printStackTrace();
        }


        assertEquals(testService.findOne(rootId).getSubCategories().size(), 3);

    }

    @Test
    public void shouldFindPointsBySubCategory() {
        ServicePointCategory root = new ServicePointCategory("root");
        ServicePointCategory child1 = new ServicePointCategory("c1");
        ServicePointCategory child2 = new ServicePointCategory("c2");
        ServicePointCategory child3 = new ServicePointCategory("c3");
        root.add(child1);
        root.add(child2);
        root.add(child3);

        ServicePoint point = new ServicePoint(new BigDecimal(1), new BigDecimal(1), new HashSet<ServicePointCategory>());
        point.getCategories().add(child1);
        servicePointCategoryRepository.save(root);
        servicePointService.create(point);


        assertEquals(servicePointService.findAll().size(), 1);
        assertEquals(servicePointService.find("root", new PageRequest(0, 100)).size(), 1);
        assertEquals(servicePointService.find("c1", new PageRequest(0, 100)).size(), 1);
        assertEquals(servicePointService.find("c2", new PageRequest(0, 100)).size(), 0);

    }

}