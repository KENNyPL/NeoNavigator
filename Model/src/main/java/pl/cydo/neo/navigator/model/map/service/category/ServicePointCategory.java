package pl.cydo.neo.navigator.model.map.service.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;
import pl.cydo.neo.navigator.model.map.point.Point;
import pl.cydo.neo.navigator.model.map.service.ServicePoint;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NodeEntity
public class ServicePointCategory {
    @GraphId
    private Long id;

    @Indexed(indexType = IndexType.LABEL, unique = true, failOnDuplicate = true)
    private String name;

    @Fetch
    @RelatedTo(type="SUB_CATEGORY", direction = Direction.OUTGOING)
    private Set<ServicePointCategory> subCategories = new HashSet<>();

    @JsonIgnore
    @RelatedTo(type = "CATEGORY_POINTS", direction = Direction.OUTGOING, elementClass = ServicePoint.class)
    private Set<ServicePoint> points = new HashSet<ServicePoint>();

    public ServicePointCategory() {
        subCategories = new HashSet<ServicePointCategory>();
    }

    public ServicePointCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ServicePointCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<ServicePointCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public boolean add(ServicePointCategory servicePointCategory) {
        return subCategories.add(servicePointCategory);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ServicePoint> getPoints() {
        return points;
    }

    public void setPoints(Set<ServicePoint> points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServicePointCategory that = (ServicePointCategory) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (points != null ? !points.equals(that.points) : that.points != null) return false;
        if (subCategories != null ? !subCategories.equals(that.subCategories) : that.subCategories != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (subCategories != null ? subCategories.hashCode() : 0);
        result = 31 * result + (points != null ? points.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ServicePointCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subCategories=" + subCategories +
                ", points=" + points +
                '}';
    }
}
