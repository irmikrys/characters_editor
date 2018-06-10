package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class Role implements Serializable {
    private int idRole;
    private String rolename;
    private Collection<Userrole> userroleByIdRole;

    public Role() {

    }

    @Id
    @Column(name = "idRole", nullable = false)
    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    @Basic
    @Column(name = "rolename", nullable = false, length = 191)
    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return idRole == role.idRole &&
                Objects.equals(rolename, role.rolename);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idRole, rolename);
    }

//    @OneToMany(mappedBy = "roleByIdRole")
//    public Collection<Userrole> getUserroleByIdRole() {
//        return userroleByIdRole;
//    }
//
//    public void setUserroleByIdRole(Collection<Userrole> userroleByIdRole) {
//        this.userroleByIdRole = userroleByIdRole;
//    }
}
