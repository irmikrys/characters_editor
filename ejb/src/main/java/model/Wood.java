package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "woods", schema = "soa_game")
public class Wood implements Serializable {

    private static final long serialVersionUID = 5793941245980666186L;
    private int idWood;
    private String name;
    private int treesNum;
    private Collection<Elf> elfByIdWood;
    private User userByIdUser;
    
    public Wood() {

    }

    public Wood(String name, int treesNum, User userByIdUser) {
        this.name = name;
        this.treesNum = treesNum;
        this.userByIdUser = userByIdUser;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idWood", nullable = false)
    public int getIdWood() {
        return idWood;
    }

    public void setIdWood(int idWood) {
        this.idWood = idWood;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "treesNum", nullable = false)
    public int getTreesNum() {
        return treesNum;
    }

    public void setTreesNum(int treesNum) {
        this.treesNum = treesNum;
    }
    
    // TODO set to .LAZY
    @OneToMany(mappedBy = "woodByIdWood", fetch = FetchType.EAGER)
    public Collection<Elf> getElfByIdWood() {
        return elfByIdWood;
    }

    public void setElfByIdWood(Collection<Elf> elfByIdWood) {
        this.elfByIdWood = elfByIdWood;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", nullable = false)
    public User getUserByIdUser() {
        return userByIdUser;
    }

    public void setUserByIdUser(User userByIdUser) {
        this.userByIdUser = userByIdUser;
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

    @Override
    public String toString() {
        return String.format("Wood[%d, %s, %d]", idWood, name, treesNum);
    }
}
