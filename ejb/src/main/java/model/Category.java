package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "categories", schema = "soa_game")
public class Category implements Serializable {

    private static final long serialVersionUID = 5793941245980666186L;

    private int idCategory;
    private String name;
    private int size;
    private Collection<Element> elements;
    private User user;
    private TypeSet typeSet;

    public Category() {

    }

    public Category(String name, int size, User user, TypeSet typeSet) {
        this.name = name;
        this.size = size;
        this.user = user;
        this.typeSet = typeSet;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategory", nullable = false)
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

    @Column(name = "size", nullable = false)
    public int getSize() {
        return size;
    }

    public void setSize(int treesNum) {
        this.size = treesNum;
    }

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    public Collection<Element> getElements() {
        return elements;
    }

    public void setElements(Collection<Element> elementByIdCategory) {
        this.elements = elementByIdCategory;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User userByIdUser) {
        this.user = userByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "idTypeSet", referencedColumnName = "idTypeSet", nullable = false)
    public TypeSet getTypeSet() {
        return typeSet;
    }

    public void setTypeSet(TypeSet typeSetByIdTypeSet) {
        this.typeSet = typeSetByIdTypeSet;
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
        return String.format("Category[%d, %s, %d, %d]", idCategory, name, size, typeSet.getIdTypeSet());
    }
}
