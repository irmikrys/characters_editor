package boundary;

import dao.ElementDAO;
import dao.UserDAO;
import dao.CategoryDAO;
import model.Category;
import model.Element;
import model.User;
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
    private CategoryDAO categoryDAO;
    @EJB
    private ElementDAO elementDAO;

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
    public LinkedList<Category> getAllCategories() {
        return new LinkedList<>(categoryDAO.findAll());
    }

    @Override
    public LinkedList<Category> getAllCategoriesWithElements() {
        return new LinkedList<>(categoryDAO.findAllWithElves());
    }

    @Override
    public Category getCategoryByIdCategory(Integer idCategory) {
        return categoryDAO.findById(idCategory).orElseThrow(NullPointerException::new);
    }

    @Override
    public void addCategory(String name, Integer size) {
        User userFromSession = userDAO.findByUsername(sessionContext.getCallerPrincipal().getName());
        categoryDAO.add(new Category(name, size, userFromSession));
    }

    @Override
    public void updateCategory(Integer idCategory, String name, Integer size) {
        categoryDAO.update(idCategory, name, size);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryDAO.remove(id);
    }

    // elements

    @Override
    public void addElement(Category category, String name, Integer quantity, Integer propType, Integer power) {
        elementDAO.add(new Element(category, name, quantity, propType, power));
    }

    @Override
    public void deleteElement(Integer id) {
        elementDAO.remove(id);
    }
}
