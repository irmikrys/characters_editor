package dao;

import model.Wood;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@SecurityDomain("soaEJBApplicationDomain")
@RolesAllowed({"User"})
public class WoodDAO extends AbstractDAO<Wood, Integer> {

    public WoodDAO() {
        this.entityClass = Wood.class;
    }

    public List<Wood> findAllWithElves() {
        TypedQuery<Wood> query = em.createQuery("SELECT w FROM Wood w JOIN FETCH w.elfByIdWood e", Wood.class);
        return query.getResultList();
    }

}
