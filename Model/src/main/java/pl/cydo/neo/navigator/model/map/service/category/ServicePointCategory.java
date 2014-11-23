package pl.cydo.neo.navigator.model.map.service.category;

import java.util.List;

public class ServicePointCategory {
    private Long id;
    private List<ServicePointCategory> subCategories;

    public ServicePointCategory(Long id) {
        this.id = id;
    }

    public List<ServicePointCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<ServicePointCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public boolean add(ServicePointCategory servicePointCategory) {
        return subCategories.add(servicePointCategory);
    }

    public ServicePointCategory remove(int index) {
        return subCategories.remove(index);
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
        if (subCategories != null ? !subCategories.equals(that.subCategories) : that.subCategories != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (subCategories != null ? subCategories.hashCode() : 0);
        return result;
    }
}
