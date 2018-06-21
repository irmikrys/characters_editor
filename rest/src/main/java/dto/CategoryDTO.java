package dto;

import model.Category;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.stream.Collectors;

@XmlRootElement
public class CategoryDTO {

    private int idCategory;
    private String name;
    private int size;
    private Collection<ElementDTO> elementDTOS;
    private int idUser;

    public CategoryDTO() {

    }

    public CategoryDTO(Category category) {
        idCategory = category.getIdCategory();
        name = category.getName();
        size = category.getSize();
        idUser = category.getIdUser();
        elementDTOS = category.getElements()
                .stream()
                .map(ElementDTO::new)
                .collect(Collectors.toList());
    }

    public CategoryDTO(String name, int size, int idUser) {
        this.name = name;
        this.size = size;
        this.idUser = idUser;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int treesNum) {
        this.size = treesNum;
    }

    public Collection<ElementDTO> getElementDTOS() {
        return elementDTOS;
    }

    public void setElementDTOS(Collection<ElementDTO> elementDTOS) {
        this.elementDTOS = elementDTOS;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return String.format("CategoryDTO[%d, %s, %d]", idCategory, name, size);
    }
}