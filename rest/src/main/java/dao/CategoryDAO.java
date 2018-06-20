package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CategoryDAO {

    @PersistenceContext(unitName = "com.irmikrys.soa")
    private EntityManager em;

}
