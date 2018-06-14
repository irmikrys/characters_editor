package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "woods", schema = "soa_game")
public class Category implements Serializable {

    private static final long serialVersionUID = 5793941245980666186L;
    private int idCategory;
    private String name;
    private int size;
    private Collection<Element> elementByIdCategory;
    private User userByIdUser;
    
    public Category() {

    }

    public Category(String name, int size, User userByIdUser) {
        this.name = name;
        this.size = size;
        this.userByIdUser = userByIdUser;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idWood", nullable = false)
    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "treesNum", nullable = false)
    public int getSize() {
        return size;
    }

    public void setSize(int treesNum) {
        this.size = treesNum;
    }
    
    // TODO set to .LAZY
    @OneToMany(mappedBy = "categoryByIdCategory", fetch = FetchType.EAGER)
    public Collection<Element> getElementByIdCategory() {
        return elementByIdCategory;
    }

    public void setElementByIdCategory(Collection<Element> elementByIdCategory) {
        this.elementByIdCategory = elementByIdCategory;
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
        Category category = (Category) o;
        return idCategory == category.idCategory &&
                size == category.size &&
                Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idCategory, name, size);
    }

    @Override
    public String toString() {
        return String.format("Category[%d, %s, %d]", idCategory, name, size);
    }
}
