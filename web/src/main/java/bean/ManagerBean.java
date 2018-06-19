package bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Named
@ApplicationScoped
public class ManagerBean implements Serializable {

    private static final long serialVersionUID = -7902579108607730057L;

    private List<String> loggedInUsers;

    public ManagerBean() {
        System.out.println("Manager bean constructor");
    }

    @PostConstruct
    public void init() {
        loggedInUsers = new LinkedList<>();
    }

    public String getSessionMessage(String username) {

        String sessionMessage;

        if (loggedInUsers.contains(username)) {
            System.out.println("User '" + username + "' already have session");
            sessionMessage = loggedInUsers.get(loggedInUsers.indexOf(username));
        } else {
            System.out.println("User '" + username + "' started new session");
            sessionMessage = "New session";
            loggedInUsers.add(username);
        }

        return sessionMessage;
    }

    public void logoutUser(String username) {
        System.out.println("user: '" + username + "' logout");
        loggedInUsers.remove(username);
    }
}
