package model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Users {
    private int idUser;
    private String username;
    private String password;
    private Collection<Userroles> userrolesByIdUser;
    private Collection<Woods> woodsByIdUser;

    @Id
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
        Users users = (Users) o;
        return idUser == users.idUser &&
                Objects.equals(username, users.username) &&
                Objects.equals(password, users.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idUser, username, password);
    }

    @OneToMany(mappedBy = "usersByIdUser")
    public Collection<Userroles> getUserrolesByIdUser() {
        return userrolesByIdUser;
    }

    public void setUserrolesByIdUser(Collection<Userroles> userrolesByIdUser) {
        this.userrolesByIdUser = userrolesByIdUser;
    }

    @OneToMany(mappedBy = "usersByIdUser")
    public Collection<Woods> getWoodsByIdUser() {
        return woodsByIdUser;
    }

    public void setWoodsByIdUser(Collection<Woods> woodsByIdUser) {
        this.woodsByIdUser = woodsByIdUser;
    }
}
