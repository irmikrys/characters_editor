package dao;

import model.TypeSet;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@SecurityDomain("soaEJBApplicationDomain")
@RolesAllowed({"User"})
public class TypeSetDAO extends AbstractDAO<TypeSet, Integer> {

    public TypeSetDAO() {
        this.entityClass = TypeSet.class;
    }

    public List<TypeSet> findAllTypeSetsFetchAll() {
        TypedQuery<TypeSet> query = em.createQuery(
                "SELECT t FROM TypeSet t " +
                        "JOIN FETCH t.categories c " +
                        "JOIN FETCH c.elements e " +
                        "JOIN FETCH c.user u ", TypeSet.class
        );
        return query.getResultList();
    }
}
