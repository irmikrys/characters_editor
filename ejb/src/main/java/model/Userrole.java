package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(UserrolePK.class)
@Table(name = "userroles")
public class Userrole implements Serializable {
    private int idUser;
    private int idRole;
//    private User userByIdUser;
//    private Role roleByIdRole;

    public Userrole() {

    }

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
        Userrole userrole = (Userrole) o;
        return idUser == userrole.idUser &&
                idRole == userrole.idRole;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idUser, idRole);
    }

//    @ManyToOne
//    @JoinColumn(name = "idUser", referencedColumnName = "idUser", nullable = false)
//    public User getUserByIdUser() {
//        return userByIdUser;
//    }
//
//    public void setUserByIdUser(User userByIdUser) {
//        this.userByIdUser = userByIdUser;
//    }
//
//    @ManyToOne
//    @JoinColumn(name = "idRole", referencedColumnName = "idRole", nullable = false)
//    public Role getRoleByIdRole() {
//        return roleByIdRole;
//    }
//
//    public void setRoleByIdRole(Role roleByIdRole) {
//        this.roleByIdRole = roleByIdRole;
//    }
}
