package model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "woods")
public class Wood {
    private int idWood;
    private String name;
    private int treesNum;
    private Collection<Elf> elfByIdWood;
    private User userByIdUser;

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
        Wood wood = (Wood) o;
        return idWood == wood.idWood &&
                treesNum == wood.treesNum &&
                Objects.equals(name, wood.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idWood, name, treesNum);
    }

    @OneToMany(mappedBy = "woodByIdWood")
    public Collection<Elf> getElfByIdWood() {
        return elfByIdWood;
    }

    public void setElfByIdWood(Collection<Elf> elfByIdWood) {
        this.elfByIdWood = elfByIdWood;
    }

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", nullable = false)
    public User getUserByIdUser() {
        return userByIdUser;
    }

    public void setUserByIdUser(User userByIdUser) {
        this.userByIdUser = userByIdUser;
    }
}
