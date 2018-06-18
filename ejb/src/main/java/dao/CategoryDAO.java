package dao;

import model.Category;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Stateless
@SecurityDomain("soaEJBApplicationDomain")
@RolesAllowed({"User"})
public class CategoryDAO extends AbstractDAO<Category, Integer> {

    public CategoryDAO() {
        this.entityClass = Category.class;
    }

    public List<Category> findAllWithElements() {
        TypedQuery<Category> query = em.createQuery(
                "SELECT c FROM Category c " +
                        "JOIN FETCH c.elements e ", Category.class);
        return query.getResultList();
    }

    public List<Category> findAllWithUser() {
        TypedQuery<Category> query = em.createQuery(
                "SELECT c FROM Category c " +
                        "JOIN FETCH c.user u ", Category.class);
        return query.getResultList();
    }

    public Optional<Category> findByIdWithUser(Integer id) {
        Optional<Category> category;
        TypedQuery<Category> query = em.createQuery(
                "SELECT c FROM Category c " +
                        "JOIN FETCH c.user u " +
                        "WHERE c.idCategory = :id", Category.class
        );
        query.setParameter("id", id);
        try {
            category = Optional.of(query.getSingleResult());
        } catch (PersistenceException e) {
            category = Optional.empty();
        }
        return category;
    }

    public void update(Integer id, String name, Integer size) {
        findById(id).ifPresent(category -> {
            if (name != null && !name.isEmpty())
                category.setName(name);
            if (size != null)
                category.setSize(size);
        });
    }

}
