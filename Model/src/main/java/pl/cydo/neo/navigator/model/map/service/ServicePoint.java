package pl.cydo.neo.navigator.model.map.service;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import pl.cydo.neo.navigator.model.map.point.Point;
import pl.cydo.neo.navigator.model.map.service.category.ServicePointCategory;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class ServicePoint extends Point {

    protected String name;
    //todo: to complete fields
    //...

    @Fetch
    @RelatedTo(type = "CATEGORY_POINTS", direction = Direction.INCOMING)
    private Set<ServicePointCategory> categories = new HashSet<>();

    public ServicePoint() {
        super();
    }

    public ServicePoint(BigDecimal latitude, BigDecimal longitude, String name) {
        super(latitude, longitude);
        this.name = name;
    }

    public ServicePoint(BigDecimal latitude, BigDecimal longitude, Set<ServicePointCategory> categories) {
        super(latitude, longitude);
        this.categories = categories;
    }

    public ServicePoint(BigDecimal latitude, BigDecimal longitude, String name, Set<ServicePointCategory> categories) {
        super(latitude, longitude);
        this.name = name;
        this.categories = categories;
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<ServicePointCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<ServicePointCategory> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ServicePoint that = (ServicePoint) o;

        if (categories != null ? !categories.equals(that.categories) : that.categories != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}