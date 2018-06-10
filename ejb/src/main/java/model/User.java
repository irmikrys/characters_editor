package model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    private int idUser;
    private String username;
    private String password;
    private Collection<Userrole> userroleByIdUser;
    private Collection<Wood> woodByIdUser;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser", nullable = false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 191)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return idUser == user.idUser &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idUser, username, password);
    }

//    @OneToMany(mappedBy = "userByIdUser")
//    public Collection<Userrole> getUserroleByIdUser() {
//        return userroleByIdUser;
//    }
//
//    public void setUserroleByIdUser(Collection<Userrole> userroleByIdUser) {
//        this.userroleByIdUser = userroleByIdUser;
//    }

    @OneToMany(mappedBy = "userByIdUser")
    public Collection<Wood> getWoodByIdUser() {
        return woodByIdUser;
    }

    public void setWoodByIdUser(Collection<Wood> woodByIdUser) {
        this.woodByIdUser = woodByIdUser;
    }
}
