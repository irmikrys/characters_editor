package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "elves", schema = "soa_game")
public class Element implements Serializable {
    private static final long serialVersionUID = -5383214602565441079L;
    private int idElement;
    private String name;
    private int arrowsNum;
    private int crossbow;
    private int power;
    private Category categoryByIdCategory;
    
    public Element() {

    }

    public Element(Category category, String name, int arrowsNum, int crossbow, int power) {
        this.categoryByIdCategory = category;
        this.name = name;
        this.arrowsNum = arrowsNum;
        this.crossbow = crossbow;
        this.power = power;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idElement", nullable = false)
    public int getIdElement() {
        return idElement;
    }

    public void setIdElement(int idElement) {
        this.idElement = idElement;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "arrowsNum", nullable = false)
    public int getArrowsNum() {
        return arrowsNum;
    }

    public void setArrowsNum(int arrowsNum) {
        this.arrowsNum = arrowsNum;
    }

    @Column(name = "crossbow", nullable = false)
    public int getCrossbow() {
        return crossbow;
    }

    public void setCrossbow(int crossbow) {
        this.crossbow = crossbow;
    }

    @Column(name = "power", nullable = false)
    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCategory", referencedColumnName = "idCategory", nullable = false)
    public Category getCategoryByIdCategory() {
        return categoryByIdCategory;
    }

    public void setCategoryByIdCategory(Category CategoryByIdCategory) {
        this.categoryByIdCategory = CategoryByIdCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return idElement == element.idElement &&
                arrowsNum == element.arrowsNum &&
                crossbow == element.crossbow &&
                power == element.power &&
                Objects.equals(name, element.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idElement, name, arrowsNum, crossbow, power);
    }

    @Override
    public String toString() {
        return String.format("Element[%d, %s, %d, %d]", idElement, name, crossbow, power);
    }
}
