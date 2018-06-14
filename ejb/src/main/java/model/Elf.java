package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "elves", schema = "soa_game")
public class Elf implements Serializable {
    private static final long serialVersionUID = -5383214602565441079L;
    private int idElf;
    private String name;
    private int arrowsNum;
    private int crossbow;
    private int power;
    private Wood woodByIdWood;
    
    public Elf() {

    }

    public Elf(Wood wood, String name, int arrowsNum, int crossbow, int power) {
        this.woodByIdWood = wood;
        this.name = name;
        this.arrowsNum = arrowsNum;
        this.crossbow = crossbow;
        this.power = power;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idElf", nullable = false)
    public int getIdElf() {
        return idElf;
    }

    public void setIdElf(int idElf) {
        this.idElf = idElf;
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
    @JoinColumn(name = "idWood", referencedColumnName = "idWood", nullable = false)
    public Wood getWoodByIdWood() {
        return woodByIdWood;
    }

    public void setWoodByIdWood(Wood woodByIdWood) {
        this.woodByIdWood = woodByIdWood;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elf elf = (Elf) o;
        return idElf == elf.idElf &&
                arrowsNum == elf.arrowsNum &&
                crossbow == elf.crossbow &&
                power == elf.power &&
                Objects.equals(name, elf.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idElf, name, arrowsNum, crossbow, power);
    }

    @Override
    public String toString() {
        return String.format("Elf[%d, %s, %d, %d]", idElf, name, crossbow, power);
    }
}
