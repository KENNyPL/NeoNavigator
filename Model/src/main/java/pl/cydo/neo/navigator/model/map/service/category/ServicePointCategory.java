package pl.cydo.neo.navigator.model.map.service.category;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.support.index.IndexType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NodeEntity
public class ServicePointCategory {
    @GraphId
    private Long id;
    @Indexed(indexType = IndexType.FULLTEXT, indexName = "ServicePointCategory.name", unique = true)
    private String name;

    @RelatedTo(type="SUB_CATEGORY", direction = Direction.OUTGOING)
    private Set<ServicePointCategory> subCategories;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServicePointCategory that = (ServicePointCategory) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (subCategories != null ? !subCategories.equals(that.subCategories) : that.subCategories != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (subCategories != null ? subCategories.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ServicePointCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subCategories=" + subCategories +
                '}';
    }
}
