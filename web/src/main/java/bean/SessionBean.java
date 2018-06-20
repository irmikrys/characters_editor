package bean;

import bean.manager.SessionManagerBean;
import boundary.CharactersServiceRemote;
import model.User;
import util.EJBUtility;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;

@SessionScoped
@Named(value = "sessionBean")
public class SessionBean implements Serializable {

    private static final long serialVersionUID = 7163621059222464579L;

    @Inject
    private SessionManagerBean sessionManager;

    private User userFromSession;

    private CharactersServiceRemote charactersServiceRemote;

    SessionBean() throws NamingException {
        System.out.println("User session bean constructor");
        charactersServiceRemote = EJBUtility.lookupCharactersService();
    }

    @PostConstruct
    public void init() {
        userFromSession = charactersServiceRemote.getUserFromSessionWithTypeSet();
        String sessionMessage = sessionManager.getSessionMessage(userFromSession.getUsername());
        System.out.println("Initialization of session bean: " + sessionMessage);
    }

    @PreDestroy
    public void destroy() {
        sessionManager.logoutUser(userFromSession.getUsername());
        System.out.println("Pre destroy: " + userFromSession.getUsername());
    }

    public User getUserFromSession() {
        return userFromSession;
    }

    public void setUserFromSession(User userFromSession) {
        this.userFromSession = userFromSession;
    }

    public void logout() {
        HttpServletRequest req = (HttpServletRequest) (FacesContext.getCurrentInstance().getExternalContext().getRequest());
        sessionManager.logoutUser(req.getRemoteUser());

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.invalidateSession();

        try {
            externalContext.redirect("index.xhtml");
        } catch (IOException e) {
            System.err.println("Redirect error");
        }
    }
}
