package dao;

import model.Category;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@SecurityDomain("soaEJBApplicationDomain")
@RolesAllowed({"User"})
public class CategoryDAO extends AbstractDAO<Category, Integer> {

    public CategoryDAO() {
        this.entityClass = Category.class;
    }

    public List<Category> findAllWithElves() {
        TypedQuery<Category> query = em.createQuery("SELECT w FROM Category w JOIN FETCH w.elementByIdCategory e", Category.class);
        return query.getResultList();
    }

}
