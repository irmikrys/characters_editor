package bean;

import boundary.CharactersServiceRemote;
import model.Category;
import util.EJBUtility;
import util.MessagesUtility;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.naming.NamingException;
import java.io.Serializable;

import static util.MessagesUtility.getParamFromContext;

@Named(value = "categoryBean")
@ViewScoped
public class CategoryBean implements Serializable {

    private static final long serialVersionUID = -735417679366680667L;

    private Integer value;
    private String name;
    private String mode;
    private Category category;
    private Integer typeSetId = 1;

    private String successMessage;
    private String errorMessage;

    private CharactersServiceRemote charactersServiceRemote;

    public CategoryBean() throws NamingException {
        System.out.println("Category bean constructor");
        charactersServiceRemote = EJBUtility.lookupCharactersService();
    }

    @PostConstruct
    public void init() {

        clearFields();
        successMessage = null;
        errorMessage = null;
        mode = "Add category";

        String idCategoryString = getParamFromContext("idCategory");

        if (idCategoryString != null) {
            category = charactersServiceRemote
                    .getCategoryByIdCategory(Integer.parseInt(idCategoryString));
            value = category.getSize();
            name = category.getName();
            mode = "Edit category";
        }
    }

    public void submitCategory() {
        if (mode.equals("Add category")) {
            try {
                addCategory();
            } catch (Exception e) {
                errorMessage = MessagesUtility.getSimpleMessageFromException(e.getMessage());
            }
        } else {
            try {
                updateCategory();
            } catch (Exception e) {
                errorMessage = MessagesUtility.getSimpleMessageFromException(e.getMessage());
            }
        }
    }

    private void addCategory() {
        charactersServiceRemote.addCategory(name, value, typeSetId);
        clearFields();
        successMessage = "Category successfully added!";
        errorMessage = null;
    }

    private void updateCategory() {
        charactersServiceRemote.updateCategory(category.getIdCategory(), name, value);
        successMessage = "Category successfully updated!";
        errorMessage = null;
    }

    private void clearFields() {
        name = null;
        value = null;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
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
