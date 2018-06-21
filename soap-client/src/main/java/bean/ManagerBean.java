package bean;

import org.soa.soap.editor.CategoryDTO;
import org.soa.soap.editor.EditorService;
import org.soa.soap.editor.EditorService_Service;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Named(value = "managerBean")
@SessionScoped
public class ManagerBean implements Serializable {

    private static final long serialVersionUID = -7902579108607730057L;

    private EditorService editorService;

    private LinkedList<CategoryDTO> categories;
    private LinkedList<CategoryDTO> categoriesForUser;
    private String categoryName;
    private String categorySize;

    ManagerBean() {
        System.out.println("Manager bean constructor");
        EditorService_Service editorService_service = new EditorService_Service();
        editorService = editorService_service.getEditorPort();
    }

    @PostConstruct
    public void init() {
        initCategories();
    }

    public void initCategories() {
        categories = (LinkedList<CategoryDTO>) editorService.getAllCategories();
        categoriesForUser = (LinkedList<CategoryDTO>) editorService.getAllCategoriesForSoapUser();
    }

    public void clearFields() {

    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(LinkedList<CategoryDTO> categories) {
        this.categories = categories;
    }

    public LinkedList<CategoryDTO> getCategoriesForUser() {
        return categoriesForUser;
    }

    public void setCategoriesForUser(LinkedList<CategoryDTO> categoriesForUser) {
        this.categoriesForUser = categoriesForUser;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategorySize() {
        return categorySize;
    }

    public void setCategorySize(String categorySize) {
        this.categorySize = categorySize;
    }
}
