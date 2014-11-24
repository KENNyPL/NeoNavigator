package pl.cydo.neo.navigator.business.repository;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.cydo.neo.navigator.model.map.service.category.ServicePointCategory;

import java.util.Iterator;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-graph-test.xml")
@Transactional
@Ignore
public class RepositoryTest extends Neo4jConfiguration {
//    @Bean
//    GraphDatabaseService graphDatabaseService() {
//        return new GraphDatabaseFactory().newEmbeddedDatabase("accessingdataneo4j.db");
//    }


    @Autowired
    private ServicePointCategoryRepository servicePointCategoryRepository;

    @Test
    public void shouldFindAll() {
        ServicePointCategory category = new ServicePointCategory();
        ServicePointCategory subCategory1 = new ServicePointCategory();
        category.setName("Jedzenie1");
        subCategory1.setName("Eco1");
        category.add(subCategory1);

        servicePointCategoryRepository.save(category);
        ServicePointCategory result = servicePointCategoryRepository.findByName("Jedzenie1");
        System.out.println("RESULT: " + result);

        int count = 0;
        Result<ServicePointCategory> categories = servicePointCategoryRepository.findAll();
        Iterator<ServicePointCategory> iterator = categories.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            count++;
        }
        System.out.println("RESULTs: " + count);
        Assert.assertEquals(result.getName(), "Jedzenie1");

    }
}
