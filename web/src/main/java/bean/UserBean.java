package bean;


import boundary.CharactersServiceRemote;
import model.User;
import util.EJBUtility;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.List;

@Named("userBean")
@ViewScoped
public class UserBean implements Serializable {

    private static final long serialVersionUID = 3432068515031339999L;

    private List<User> users;

    private CharactersServiceRemote charactersServiceRemote;

    UserBean() throws NamingException {
        charactersServiceRemote = EJBUtility.lookupCharactersService();
        System.out.println("User bean");
    }

    @PostConstruct
    public void init() {
        users = charactersServiceRemote.getAllUsers();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
