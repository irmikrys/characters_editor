package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(UserrolesPK.class)
public class Userroles {
    private int idUser;
    private int idRole;
    private Users usersByIdUser;
    private Roles rolesByIdRole;

    @Id
    @Column(name = "idUser", nullable = false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Id
    @Column(name = "idRole", nullable = false)
    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Userroles userroles = (Userroles) o;
        return idUser == userroles.idUser &&
                idRole == userroles.idRole;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idUser, idRole);
    }

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", nullable = false)
    public Users getUsersByIdUser() {
        return usersByIdUser;
    }

    public void setUsersByIdUser(Users usersByIdUser) {
        this.usersByIdUser = usersByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "idRole", referencedColumnName = "idRole", nullable = false)
    public Roles getRolesByIdRole() {
        return rolesByIdRole;
    }

    public void setRolesByIdRole(Roles rolesByIdRole) {
        this.rolesByIdRole = rolesByIdRole;
    }
}
