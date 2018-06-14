package dao;

import model.Element;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
@SecurityDomain("soaEJBApplicationDomain")
@RolesAllowed({"User"})
public class ElementDAO extends AbstractDAO<Element, Integer> {

    public ElementDAO() {
        this.entityClass = Element.class;
    }

    public Element findWithCategory() {
        TypedQuery<Element> query = em.createQuery("SELECT e FROM Element e JOIN FETCH e.categoryByIdCategory w", Element.class);
        return query.getSingleResult();
    }

}
