package pl.cydo.neo.navigator.model.map.service;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import pl.cydo.neo.navigator.model.map.point.Point;
import pl.cydo.neo.navigator.model.map.service.category.ServicePointCategory;

import java.util.List;

@NodeEntity
public class ServicePoint extends Point {
    private List<ServicePointCategory> categories;

    public List<ServicePointCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ServicePointCategory> categories) {
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