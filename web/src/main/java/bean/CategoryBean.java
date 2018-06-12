package bean;

import boundary.CharactersServiceRemote;
import util.EJBUtility;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.naming.NamingException;
import java.io.Serializable;

@Named(value = "categoryBean")
@ViewScoped
public class CategoryBean implements Serializable {

    private static final long serialVersionUID = -735417679366680667L;

    private Integer value;
    private String name;
    private String mode;
    private String successMessage;

    private CharactersServiceRemote charactersServiceRemote;

    public CategoryBean() throws NamingException {
        System.out.println("Category bean constructor");
        charactersServiceRemote = EJBUtility.lookupCharactersService();
    }

    @PostConstruct
    public void init() {
        successMessage = "";
        mode = "Add category";
    }

    public void submitCategory() {
        if(name != null && value != null) {
            //add category with bean to db
            System.out.format("Submitting category: name %s, quantity %d", name, value);
            successMessage = "Category successfully submitted!";
            name = null;
            value = null;
        }
        System.out.println("Submitting ended.");
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
}
