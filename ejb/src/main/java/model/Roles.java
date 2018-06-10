package model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Roles {
    private int idRole;
    private String rolename;
    private Collection<Userroles> userrolesByIdRole;

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
        Roles roles = (Roles) o;
        return idRole == roles.idRole &&
                Objects.equals(rolename, roles.rolename);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idRole, rolename);
    }

    @OneToMany(mappedBy = "rolesByIdRole")
    public Collection<Userroles> getUserrolesByIdRole() {
        return userrolesByIdRole;
    }

    public void setUserrolesByIdRole(Collection<Userroles> userrolesByIdRole) {
        this.userrolesByIdRole = userrolesByIdRole;
    }
}
