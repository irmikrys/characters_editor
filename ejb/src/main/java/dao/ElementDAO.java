package dao;

import model.Element;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Stateless
@SecurityDomain("soaEJBApplicationDomain")
@RolesAllowed({"User"})
public class ElementDAO extends AbstractDAO<Element, Integer> {

    public ElementDAO() {
        this.entityClass = Element.class;
    }

    public Optional<Element> findWithCategory(Integer id) {
        Optional<Element> element;
        TypedQuery<Element> query = em.createQuery(
                "SELECT e FROM Element e " +
                        "JOIN FETCH e.categoryByIdCategory c " +
                        "WHERE e.idElement = :id", Element.class
        );
        query.setParameter("id", id);
        try {
            element = Optional.of(query.getSingleResult());
        } catch (PersistenceException e) {
            element = Optional.empty();
        }
        return element;
    }

    public Optional<Element> findFetchAllByIdElement(Integer id) {
        Optional<Element> element;
        TypedQuery<Element> query = em.createQuery(
           "SELECT e FROM Element e " +
                   "JOIN FETCH e.categoryByIdCategory c " +
                   "JOIN FETCH c.userByIdUser u " +
                   "WHERE e.idElement = :id", Element.class
        );
        query.setParameter("id", id);
        try {
            element = Optional.of(query.getSingleResult());
        } catch (PersistenceException e) {
            element = Optional.empty();
        }
        return element;
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
