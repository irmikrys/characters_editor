package dao;

import model.Elf;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
@SecurityDomain("soaEJBApplicationDomain")
@RolesAllowed({"User"})
public class ElfDAO extends AbstractDAO<Elf, Integer> {

    public ElfDAO() {
        this.entityClass = Elf.class;
    }

    public Elf findWithWood() {
        TypedQuery<Elf> query = em.createQuery("SELECT e FROM Elf e JOIN FETCH e.woodByIdWood w", Elf.class);
        return query.getSingleResult();
    }

}
