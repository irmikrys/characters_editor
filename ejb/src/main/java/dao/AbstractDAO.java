package dao;

import javax.annotation.security.PermitAll;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@PermitAll
public abstract class AbstractDAO<T, ID> {

    protected Class<T> entityClass;

    @PersistenceContext(unitName = "SOA")
    protected EntityManager em;

    public Optional<T> findById(ID id) {
        return Optional.ofNullable(em.find(entityClass, id));
    }

    public List<T> findAll() {
        CriteriaQuery<T> criteria = em.getCriteriaBuilder().createQuery(entityClass);
        criteria.from(entityClass);

        return em.createQuery(criteria).getResultList();
    }

    public void add(T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    public void remove(ID id) {
        findById(id).ifPresent(e -> {
            em.getTransaction().begin();
            em.remove(e);
            em.getTransaction().commit();
        });
    }
}
