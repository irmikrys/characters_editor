package boundary;

import dao.CategoryDAO;
import dao.ElementDAO;
import dao.UserDAO;
import exception.*;
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
import javax.persistence.PersistenceException;
import java.util.LinkedList;

@Stateless
@SecurityDomain("soaEJBApplicationDomain")
@RolesAllowed({"User"})
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

    // users

    @Override
    public LinkedList<User> getAllUsers() {
        return new LinkedList<>(userDAO.findAll());
    }

    @Override
    public boolean userExists(String username) {
        return userDAO.existsByUsername(username);
    }

    @Override
    public void updatePassword(String username, String newPassword) {
        if (hasModificationRights(username)) {
            User userToUpdate = userDAO.findByUsername(username).orElseThrow(
                    () -> new UserNotFoundException("User not found")
            );
            if (newPassword == null || newPassword.isEmpty()) {
                throw new WrongFormatException("Wrong password format");
            } else {
                try {
                    userDAO.update(userToUpdate.getIdUser(), username, newPassword);
                } catch (PersistenceException e) {
                    throw new PersistenceException("Cannot update password for user " + username);
                }
            }
        } else {
            throw new AccessDeniedException("You have no rights to change this password");
        }
    }

    // categories

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
        return categoryDAO.findById(idCategory).orElseThrow(
                () -> new CategoryNotFoundException("Cannot get category by id " + idCategory)
        );
    }

    @Override
    public void addCategory(String name, Integer size) {
        User userFromSession = userDAO.findByUsername(sessionContext.getCallerPrincipal().getName())
                .orElseThrow(
                        () -> new UserNotFoundException("User logged in not found")
                );
        try {
            categoryDAO.add(new Category(name, size, userFromSession));
        } catch (PersistenceException e) {
            throw new PersistenceException("Cannot add category");
        }
    }

    @Override
    public void updateCategory(Integer idCategory, String name, Integer size) {
        Category categoryToUpdate = categoryDAO.findById(idCategory).orElseThrow(
                () -> new CategoryNotFoundException("Cannot find category to update by id " + idCategory)
        );
        if (hasModificationRights(categoryToUpdate.getUserByIdUser().getUsername())) {
            try {
                categoryDAO.update(idCategory, name, size);
            } catch (PersistenceException e) {
                throw new PersistenceException("Cannot update category " + idCategory);
            }
        } else {
            throw new AccessDeniedException("You have no rights to update category");
        }
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryDAO.remove(id);
    }

    // elements

    @Override
    public Element getElementByIdElement(Integer idElement) {
        return elementDAO.findById(idElement).orElseThrow(
                () -> new ElementNotFoundException("Cannot get element by id " + idElement)
        );
    }

    @Override
    public Element getElementWithCategoryByIdElement(Integer idElement) {
        return elementDAO.findWithCategory(idElement).orElseThrow(
                () -> new ElementNotFoundException("Cannot get element by id " + idElement)
        );
    }

    @Override
    public void addElement(Category category, String name, Integer quantity, Integer propType, Integer power) {
        try {
            elementDAO.add(new Element(category, name, quantity, propType, power));
        } catch (PersistenceException e) {
            throw new PersistenceException("Cannot add element");
        }
    }

    @Override
    public void updateElement(Integer idCategory, Integer idElement, String name, Integer fortune, Integer propType, Integer power) {
        Element element = elementDAO.findWithCategory(idElement).orElseThrow(
                () -> new ElementNotFoundException("Cannot find element to update by id " + idElement)
        );
        if (hasModificationRights(element.getCategoryByIdCategory().getUserByIdUser().getUsername())) {
            try {
                Category category = categoryDAO.findById(idCategory).orElseThrow(
                        () -> new CategoryNotFoundException("Cannot find element category by id " + idCategory)
                );
                elementDAO.update(category, idElement, name, fortune, propType, power);
            } catch (PersistenceException e) {
                throw new PersistenceException("Cannot update element " + idElement);
            }
        } else {
            throw new AccessDeniedException("You have no rights to update element");
        }
    }

    @Override
    public void deleteElement(Integer id) {
        elementDAO.remove(id);
    }

    private boolean hasModificationRights(String username) {
        User loggedUser = userDAO.findByUsername(sessionContext.getCallerPrincipal().getName())
                .orElseThrow(() -> new UserNotFoundException("User logged in not found"));
        return loggedUser.getUsername().equals(username) || sessionContext.isCallerInRole("Manager");
    }
}
