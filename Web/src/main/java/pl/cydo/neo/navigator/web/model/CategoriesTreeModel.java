package pl.cydo.neo.navigator.web.model;

import pl.cydo.neo.navigator.model.map.service.category.ServicePointCategory;

import java.util.LinkedList;
import java.util.List;

public class CategoriesTreeModel {
    private String text;
    private State state;
    private List<CategoriesTreeModel> children= new LinkedList<>();

    public CategoriesTreeModel() {
    }

    public CategoriesTreeModel(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean add(CategoriesTreeModel categoriesTreeModel) {
        return children.add(categoriesTreeModel);
    }

    public List<CategoriesTreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<CategoriesTreeModel> children) {
        this.children = children;
    }

    public static CategoriesTreeModel from(ServicePointCategory category) {
        CategoriesTreeModel element = new CategoriesTreeModel(category.getName());
        for(ServicePointCategory subCategory:category.getSubCategories()){
            element.add(from(subCategory));
        }
        return element;
    }
}
