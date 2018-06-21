package dao;

import model.Category;

import javax.ejb.Stateless;

@Stateless
public class CategoryDAO extends AbstractDAO<Category, Integer> {

    public CategoryDAO() {
        this.entityClass = Category.class;
    }

}
