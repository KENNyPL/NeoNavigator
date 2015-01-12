package pl.cydo.neo.navigator.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.cydo.neo.navigator.business.repository.ServicePointCategoryRepository;
import pl.cydo.neo.navigator.business.repository.ZoneRepository;
import pl.cydo.neo.navigator.model.map.service.category.ServicePointCategory;
import scala.collection.mutable.HashSet;

import java.util.Set;

@Service
public class TestService {

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private ServicePointCategoryRepository servicePointCategoryRepository;

    @Autowired
    private ServicePointService servicePointService;

    public TestService() {
    }

    public <S extends ServicePointCategory> S save(S entity) {
        return servicePointCategoryRepository.save(entity);
    }

    public ServicePointCategory findOne(Long aLong) {
        return servicePointCategoryRepository.findOne(aLong);
    }

    public ServicePointCategory findByName(String name) {
        return servicePointCategoryRepository.findByName(name);
    }

    public Long getCount() {
        return servicePointCategoryRepository.count();
    }

    @Transactional
    public Set<ServicePointCategory> findAll() {
        return servicePointCategoryRepository.findAll().as(Set.class);
    }

    @Transactional
    public void addSubCategory(Long parentId, String name) {
        ServicePointCategory parent = null;
        if(parentId==-1){
            parent= servicePointCategoryRepository.findByName("root");
        }else{
            parent= servicePointCategoryRepository.findOne(parentId);
        }
        parent.add(new ServicePointCategory(name));
        servicePointCategoryRepository.save(parent);
    }

    @Transactional
    public void rename(Long id, String name) {
        ServicePointCategory node = servicePointCategoryRepository.findOne(id);
        node.setName(name);
        servicePointCategoryRepository.save(node);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(Long id) {
        deleteCategory(id);
    }
    private void deleteCategory(Long id) {
        ServicePointCategory nodeToDelete = servicePointCategoryRepository.findOne(id);

        if(nodeToDelete.getPoints().size()>0){
            throw new UnsupportedOperationException();
        }

        if(nodeToDelete.getSubCategories()!= null && nodeToDelete.getSubCategories().size()>0){
            for(ServicePointCategory subCategory: nodeToDelete.getSubCategories()) {
                delete(subCategory.getId());
            }
        }
        System.out.println("delete->");
        servicePointCategoryRepository.delete(id);
    }
}
