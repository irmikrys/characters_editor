package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "categories", schema = "soa_game")
public class Category implements Serializable {

    private static final long serialVersionUID = 5793941245980666186L;

    private int idCategory;
    private String name;
    private int size;
    private Collection<Element> elements;
    private int idUser;

    public Category() {

    }

    public Category(String name, int size, int idUser) {
        this.name = name;
        this.size = size;
        this.idUser = idUser;
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

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    public Collection<Element> getElements() {
        return elements;
    }

    public void setElements(Collection<Element> elements) {
        this.elements = elements;
    }

    @Column(name = "idUser", nullable = false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return String.format("Category[%d, %s, %d]", idCategory, name, size);
    }
}
