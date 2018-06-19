package bean;

import boundary.CharactersServiceRemote;
import model.Category;
import model.User;
import util.EJBUtility;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.LinkedList;

@SessionScoped
@Named(value = "sessionBean")
public class SessionBean implements Serializable {

    private static final long serialVersionUID = 7163621059222464579L;

    private User userFromSession;
    private LinkedList<Category> userCategories;

    private CharactersServiceRemote charactersServiceRemote;

    SessionBean() {
        System.out.println("User session bean constructor");
    }

    @PostConstruct
    public void init() throws NamingException {
        charactersServiceRemote = EJBUtility.lookupCharactersService();
        userFromSession = charactersServiceRemote.getUserFromSessionWithTypeSet();
        userCategories = charactersServiceRemote.getCategoriesBySessionUser();
    }

    public void updateCategories() {
        userCategories = charactersServiceRemote.getCategoriesBySessionUser();
    }

    public User getUserFromSession() {
        return userFromSession;
    }

    public void setUserFromSession(User userFromSession) {
        this.userFromSession = userFromSession;
    }

    public LinkedList<Category> getUserCategories() {
        return userCategories;
    }

    public void setUserCategories(LinkedList<Category> userCategories) {
        this.userCategories = userCategories;
    }
}
