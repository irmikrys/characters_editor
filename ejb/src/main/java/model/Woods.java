package model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Woods {
    private int idWood;
    private String name;
    private int treesNum;
    private Collection<Elves> elvesByIdWood;
    private Users usersByIdUser;

    @Id
    @Column(name = "idWood", nullable = false)
    public int getIdWood() {
        return idWood;
    }

    public void setIdWood(int idWood) {
        this.idWood = idWood;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "treesNum", nullable = false)
    public int getTreesNum() {
        return treesNum;
    }

    public void setTreesNum(int treesNum) {
        this.treesNum = treesNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Woods woods = (Woods) o;
        return idWood == woods.idWood &&
                treesNum == woods.treesNum &&
                Objects.equals(name, woods.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idWood, name, treesNum);
    }

    @OneToMany(mappedBy = "woodsByIdWood")
    public Collection<Elves> getElvesByIdWood() {
        return elvesByIdWood;
    }

    public void setElvesByIdWood(Collection<Elves> elvesByIdWood) {
        this.elvesByIdWood = elvesByIdWood;
    }

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", nullable = false)
    public Users getUsersByIdUser() {
        return usersByIdUser;
    }

    public void setUsersByIdUser(Users usersByIdUser) {
        this.usersByIdUser = usersByIdUser;
    }
}
