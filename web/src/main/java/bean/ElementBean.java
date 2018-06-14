package bean;

import boundary.CharactersServiceRemote;
import model.Category;
import model.Element;
import util.EJBUtility;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
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
    private List<Category> categories;
    private Category selectedCategory;
    private String successMessage;

    private Element element;

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
        categories = charactersServiceRemote.getAllCategories();
        if (categories.size() != 0) {
            selectedCategory = categories.get(0);
        }

        String idParamString =
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getRequestParameterMap()
                        .get("id");

        if (idParamString != null) {
            mode = "Edit element";
            element = charactersServiceRemote
                    .getElementWithCategoryByIdElement(Integer.parseInt(idParamString));
            setParamsByElement(element);
        }
    }

    public void submitElement() {

        if (mode.equals("Add element")) {
            addElement();
        } else {
            updateElement();
        }
        successMessage = "Element successfully submitted!";
    }

    private void addElement() {
        charactersServiceRemote.addElement(selectedCategory, name, quantity, propType, power);
        clearFields();
    }

    private void updateElement() {
        charactersServiceRemote.updateElement(element.getIdElement(), name, quantity, propType, power);
    }

    private void setParamsByElement(Element element) {
        name = element.getName();
        quantity = element.getArrowsNum();
        propType = element.getCrossbow();
        power = element.getPower();
        selectedCategory = element.getCategoryByIdCategory();
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Category getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Category selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }
}
