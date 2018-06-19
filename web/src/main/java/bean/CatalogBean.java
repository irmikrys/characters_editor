package bean;

import boundary.CharactersServiceRemote;
import model.Category;
import model.Element;
import model.TreeNodeData;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import util.EJBUtility;
import util.MessagesUtility;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Named(value = "catalogBean")
@ViewScoped
public class CatalogBean implements Serializable {

    private static final long serialVersionUID = -9217712787886869451L;

    private CharactersServiceRemote charactersServiceRemote;
    private TreeNode root;
    private LinkedList<LinkedList<Element>> bestElements;
    private String errorMessage;

    public CatalogBean() throws NamingException {

        charactersServiceRemote = EJBUtility.lookupCharactersService();
        System.out.println("Catalog charactersServiceRemote constructor end");
    }

    @PostConstruct
    public void init() {
        initDataView();
        initBestElementsList();
        errorMessage = null;
    }

    public void deleteElement(String type, Integer id) {

        try {
            if (type.equals(Category.class.getSimpleName())) {
                charactersServiceRemote.deleteCategory(id);
                errorMessage = null;
            } else if (type.equals(Element.class.getSimpleName())) {
                charactersServiceRemote.deleteElement(id);
                errorMessage = null;
            } else {
                System.out.println("Delete element: Unknown type");
            }
            initDataView();
            initBestElementsList();
        } catch (Exception e) {
            errorMessage = MessagesUtility.getSimpleMessageFromException(e.getMessage());
            initBestElementsList();
        }
    }

    private void initBestElementsList() {
        System.out.println("Initialization of best elements...");
        bestElements = new LinkedList<>();
        bestElements = charactersServiceRemote.getBestElementsForTypeSets();
        bestElements.forEach(list -> list.forEach(System.out::println));
    }

    private void initDataView() {
        root = new DefaultTreeNode(new TreeNodeData(null, null, "Categories", null), null);
        this.getCategories().forEach(
                category -> addNode(
                        root,
                        new TreeNodeData(
                                category.getClass().getSimpleName(),
                                category.getIdCategory(),
                                category.getName(),
                                category.getUser().getTypeSet().getIdTypeSet()
                        ),
                        charactersServiceRemote.getElementsByIdCategory(category.getIdCategory()),
                        category.getUser().getTypeSet().getIdTypeSet()
                ));
    }

    private void addNode(TreeNode parentNode, TreeNodeData data, Collection<Element> elements, Integer typeSetId) {
        TreeNode node = new DefaultTreeNode(data, parentNode);
        node.setExpanded(true);
        if (elements != null) {
            for (Element e : elements) {
                addNode(node,
                        new TreeNodeData(
                                e.getClass().getSimpleName(),
                                e.getIdElement(),
                                e.getName(),
                                typeSetId
                        ),
                        null,
                        typeSetId
                );
            }
        }
    }

    private List<Category> getCategories() {
        return new LinkedList<>(charactersServiceRemote.getAllCategories());
    }

    public TreeNode getRoot() {
        return root;
    }

    public LinkedList<LinkedList<Element>> getBestElements() {
        return bestElements;
    }

    public void setBestElements(LinkedList<LinkedList<Element>> bestElements) {
        this.bestElements = bestElements;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
