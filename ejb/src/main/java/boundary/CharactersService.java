package boundary;

import dao.ElfDAO;
import dao.UserDAO;
import dao.WoodDAO;
import model.Elf;
import model.User;
import model.Wood;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import java.util.LinkedList;

@Stateless
@SecurityDomain("soaEJBApplicationDomain")
@RolesAllowed({ "User" })
@Remote(CharactersServiceRemote.class)
public class CharactersService implements CharactersServiceRemote {

    @Resource
    private SessionContext sessionContext;

    @EJB
    private UserDAO userDAO;
    @EJB
    private WoodDAO woodDAO;
    @EJB
    private ElfDAO elfDAO;

    @Override
    public String getHello() {
        System.out.println("EJB:");
        System.out.println("    principal: " + sessionContext.getCallerPrincipal());
        System.out.println("    user:      " + sessionContext.isCallerInRole("User"));
        System.out.println("    manager:   " + sessionContext.isCallerInRole("Manager"));
        return "Hello from Characters service!";
    }

    @Override
    public LinkedList<User> getAllUsers() {
        return new LinkedList<>(userDAO.findAll());
    }

    @Override
    public LinkedList<Wood> getAllWoods() {
        return new LinkedList<>(woodDAO.findAll());
    }

    @Override
    public LinkedList<Wood> getAllWoodsWithElves() {
        return new LinkedList<>(woodDAO.findAllWithElves());
    }

    @Override
    public void addWood(String name, Integer size) {
        User userFromSession = userDAO.findByUsername(sessionContext.getCallerPrincipal().getName());
        woodDAO.add(new Wood(name, size, userFromSession));
        System.out.format("Adding wood in service: %s, %d, %s", name, size, userFromSession.getUsername());
    }

    @Override
    public Wood getWoodByIdWood(Integer idWood) {
        return woodDAO.findById(idWood).orElseThrow(NullPointerException::new);
    }

    // elements

    @Override
    public void addElf(Wood wood, String name, Integer quantity, Integer propType, Integer power) {
        elfDAO.add(new Elf(wood, name, quantity, propType, power));
    }
}
