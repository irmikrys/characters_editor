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

    public Element findWithCategory(Integer id) {
        TypedQuery<Element> query = em.createQuery(
                "SELECT e FROM Element e " +
                        "JOIN FETCH e.categoryByIdCategory w " +
                        "WHERE e.idElement = :id", Element.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public void update(Integer id, String name, Integer fortune, Integer propType, Integer power) {
        findById(id).ifPresent(element -> {
            if (name != null && !name.isEmpty())
                element.setName(name);
            if (fortune != null)
                element.setArrowsNum(fortune);
            if (propType != null)
                element.setCrossbow(propType);
            if (power != null)
                element.setPower(power);
        });
    }

}
