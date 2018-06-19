package bean;

import boundary.CharactersServiceRemote;
import model.User;
import util.EJBUtility;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.naming.NamingException;
import java.io.Serializable;

@SessionScoped
@Named(value = "sessionBean")
public class SessionBean implements Serializable {

    private static final long serialVersionUID = 7163621059222464579L;

    private User userFromSession;

    private CharactersServiceRemote charactersServiceRemote;

    SessionBean() throws NamingException {
        System.out.println("User session bean constructor");
        charactersServiceRemote = EJBUtility.lookupCharactersService();
    }

    @PostConstruct
    public void init() {
        userFromSession = charactersServiceRemote.getUserFromSessionWithTypeSet();
    }

    public User getUserFromSession() {
        return userFromSession;
    }

    public void setUserFromSession(User userFromSession) {
        this.userFromSession = userFromSession;
    }

}
