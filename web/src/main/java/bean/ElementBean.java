package bean;

import boundary.CharactersServiceRemote;
import model.Category;
import model.Element;
import util.EJBUtility;
import util.MessagesUtility;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.List;

import static util.MessagesUtility.getParamFromContext;

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
    private String errorMessage;

    private Element element;

    private CharactersServiceRemote charactersServiceRemote;

    public ElementBean() throws NamingException {
        System.out.println("Element bean constructor");
        charactersServiceRemote = EJBUtility.lookupCharactersService();
    }

    @PostConstruct
    public void init() {

        clearFields();
        successMessage = null;
        mode = "Add element";
        categories = charactersServiceRemote.getAllCategories();
        if (categories.size() != 0) {
            selectedCategory = categories.get(0);
        }

        String idElementString = getParamFromContext("idElement");
        String idCategoryString = getParamFromContext("idCategory");

        if (idElementString != null && !idElementString.equals("0")) {
            mode = "Edit element";
            element = charactersServiceRemote
                    .getElementWithCategoryByIdElement(Integer.parseInt(idElementString));
            setParamsByElement(element);
        }

        if (idCategoryString != null) {
            selectedCategory = charactersServiceRemote
                    .getCategoryByIdCategory(Integer.parseInt(idCategoryString));
        }
    }

    public void categoryChanged(AjaxBehaviorEvent e) {
        System.out.println("Selected category: " + selectedCategory.getIdCategory());
    }

    public void submitElement() {
        if (mode.equals("Add element")) {
            try {
                addElement();
            } catch (Exception e) {
                errorMessage = MessagesUtility.getSimpleMessageFromException(e.getMessage());
            }
        } else {
            try {
                updateElement();
            } catch (Exception e) {
                errorMessage = MessagesUtility.getSimpleMessageFromException(e.getMessage());
            }
        }
    }

    private void addElement() {
        charactersServiceRemote.addElement(selectedCategory, name, quantity, propType, power);
        clearFields();
        successMessage = "Element successfully added!";
        errorMessage = null;
    }

    private void updateElement() {
        charactersServiceRemote.updateElement(
                selectedCategory.getIdCategory(),
                element.getIdElement(),
                name, quantity, propType, power
        );
        successMessage = "Element successfully updated!";
        errorMessage = null;
    }

    private void setParamsByElement(Element element) {
        name = element.getName();
        quantity = element.getFortune();
        propType = element.getProperty();
        power = element.getPower();
        selectedCategory = element.getCategoryByIdCategory();
    }

    private void clearFields() {
        System.out.println("Element bean: clearing fields...");
        name = null;
        quantity = null;
        propType = null;
        power = null;
        selectedCategory = null;
        element = new Element();
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

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
