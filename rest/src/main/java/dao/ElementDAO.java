package dao;

import model.Element;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ElementDAO {

    @PersistenceContext(unitName = "com.irmikrys.soa")
    private EntityManager em;

    public void add(Element element) {
        em.persist(element);
    }

}
