package dao;

import model.User;
import org.jboss.ejb3.annotation.SecurityDomain;
import util.PasswordEncoder;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

@Stateless
@SecurityDomain("soaEJBApplicationDomain")
@RolesAllowed({ "User" })
public class UserDAO extends AbstractDAO<User, Long> {

    public UserDAO() {
        this.entityClass = User.class;
    }

    public void update(Long idUser, String username, String password) {
        findById(idUser).ifPresent(user -> {
            em.getTransaction().begin();
            if (username != null && !username.isEmpty())
                user.setUsername(username);
            if (password != null && !password.isEmpty())
                user.setPassword(PasswordEncoder.getEncodedPassword(password));
            em.getTransaction().commit();
        });
    }
}
