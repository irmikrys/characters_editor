package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Elves {
    private int idElf;
    private String name;
    private int arrowsNum;
    private int crossbow;
    private int power;
    private Woods woodsByIdWood;

    @Id
    @Column(name = "idElf", nullable = false)
    public int getIdElf() {
        return idElf;
    }

    public void setIdElf(int idElf) {
        this.idElf = idElf;
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
    @Column(name = "arrowsNum", nullable = false)
    public int getArrowsNum() {
        return arrowsNum;
    }

    public void setArrowsNum(int arrowsNum) {
        this.arrowsNum = arrowsNum;
    }

    @Basic
    @Column(name = "crossbow", nullable = false)
    public int getCrossbow() {
        return crossbow;
    }

    public void setCrossbow(int crossbow) {
        this.crossbow = crossbow;
    }

    @Basic
    @Column(name = "power", nullable = false)
    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elves elves = (Elves) o;
        return idElf == elves.idElf &&
                arrowsNum == elves.arrowsNum &&
                crossbow == elves.crossbow &&
                power == elves.power &&
                Objects.equals(name, elves.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idElf, name, arrowsNum, crossbow, power);
    }

    @ManyToOne
    @JoinColumn(name = "idWood", referencedColumnName = "idWood", nullable = false)
    public Woods getWoodsByIdWood() {
        return woodsByIdWood;
    }

    public void setWoodsByIdWood(Woods woodsByIdWood) {
        this.woodsByIdWood = woodsByIdWood;
    }
}
