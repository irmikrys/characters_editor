package dao;

import model.Wood;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.ejb.Stateless;

@Stateless
@SecurityDomain("soaEJBApplicationDomain")
public class WoodDAO extends AbstractDAO<Wood, Long> {

    public WoodDAO() {
        this.entityClass = Wood.class;
    }

}
