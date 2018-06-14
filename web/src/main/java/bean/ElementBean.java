package bean;

import boundary.CharactersServiceRemote;
import model.Wood;
import util.EJBUtility;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.List;

@Named(value = "elementBean")
@ViewScoped
public class ElementBean implements Serializable {

    private static final long serialVersionUID = -8739078788314342985L;

    private String name;
    private Integer quantity;
    private Integer propType;
    private Integer power;
    private String mode;
    private List<Wood> categories;
    private Wood selectedCategory;
    private String successMessage;

    private CharactersServiceRemote charactersServiceRemote;

    public ElementBean() throws NamingException {
        System.out.println("Element bean constructor");
        charactersServiceRemote = EJBUtility.lookupCharactersService();
    }

    @PostConstruct
    public void init() {

        clearFields();
        successMessage = "";
        mode = "Add element";
        categories = charactersServiceRemote.getAllWoods();
        if(categories.size() != 0) {
            selectedCategory = categories.get(0);
        }
    }

    public void submitElement() {
        System.out.format("Selected category: %s, %d, %d \n",
                selectedCategory.getName(),
                selectedCategory.getIdWood(),
                selectedCategory.getTreesNum()
        );
        charactersServiceRemote.addElf(selectedCategory, name, quantity, propType, power);
        successMessage = "Element successfully submitted!";
        clearFields();
    }

    private void clearFields() {
        name = null;
        quantity = null;
        propType = null;
        power = null;
        selectedCategory = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPropType() {
        return propType;
    }

    public void setPropType(Integer propType) {
        this.propType = propType;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public List<Wood> getCategories() {
        return categories;
    }

    public void setCategories(List<Wood> categories) {
        this.categories = categories;
    }

    public Wood getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Wood selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }
}
