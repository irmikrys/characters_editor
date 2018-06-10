package boundary;

import dao.UserDAO;
import dao.WoodDAO;
import model.User;
import model.Wood;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import java.util.LinkedList;

@Stateless
@SecurityDomain("soaEJBApplicationDomain")
@Remote(CharactersServiceRemote.class)
public class CharactersService implements CharactersServiceRemote {

    @Resource
    private SessionContext sessionContext;

    @EJB
    private UserDAO userDAO;

    @EJB
    private WoodDAO woodDAO;

    @Override
    @PermitAll
    public String getHello() {
        System.out.println("EJB:");
        System.out.println("    principal: " + sessionContext.getCallerPrincipal());
        System.out.println("    user:      " + sessionContext.isCallerInRole("User"));
        System.out.println("    manager:   " + sessionContext.isCallerInRole("Manager"));
        return "Hello from Characters service!";
    }

    @Override
    @PermitAll
    public LinkedList<User> getAllUsers() {
        return new LinkedList<>(userDAO.findAll());
    }

    @Override
    @PermitAll
    public LinkedList<Wood> getAllWoods() {
        return new LinkedList<>(woodDAO.findAll());
    }
}
