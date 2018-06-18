package dao;

import model.TypeSet;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

@Stateless
@SecurityDomain("soaEJBApplicationDomain")
@RolesAllowed({"User"})
public class TypeSetDAO extends AbstractDAO<TypeSet, Integer> {

    public TypeSetDAO() {
        this.entityClass = TypeSet.class;
    }

}
