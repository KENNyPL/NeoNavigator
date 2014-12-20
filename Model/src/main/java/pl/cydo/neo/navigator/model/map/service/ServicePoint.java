package pl.cydo.neo.navigator.model.map.service;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import pl.cydo.neo.navigator.model.map.point.Point;
import pl.cydo.neo.navigator.model.map.service.category.ServicePointCategory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@NodeEntity
public class ServicePoint extends Point {

    @Fetch
    @RelatedTo(type="CATEGORY", direction = Direction.INCOMING)
    private Set<ServicePointCategory> categories;

    public ServicePoint() {
        super();
    }

    public ServicePoint(BigDecimal latitude, BigDecimal longitude, Set<ServicePointCategory> categories) {
        super(latitude, longitude);
        this.categories = categories;
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

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (categories != null ? categories.hashCode() : 0);
        return result;
    }
}