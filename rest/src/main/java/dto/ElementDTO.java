package dto;

import model.Element;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ElementDTO {

    private String name;
    private int fortune;
    private int property;
    private int power;

    public ElementDTO() {

    }

    public ElementDTO(Element element) {
        name = element.getName();
        fortune = element.getFortune();
        property = element.getProperty();
        power = element.getPower();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFortune() {
        return fortune;
    }

    public void setFortune(int arrowsNum) {
        this.fortune = arrowsNum;
    }

    public int getProperty() {
        return property;
    }

    public void setProperty(int crossbow) {
        this.property = crossbow;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return String.format("ElementDTO[%s, %d, %d]", name, property, power);
    }
}
