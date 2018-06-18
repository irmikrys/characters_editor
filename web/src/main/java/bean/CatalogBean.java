package bean;

import boundary.CharactersServiceRemote;
import model.Category;
import model.Element;
import model.TreeNodeData;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import util.EJBUtility;

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

    public CatalogBean() throws NamingException {

        charactersServiceRemote = EJBUtility.lookupCharactersService();
        System.out.println("Catalog charactersServiceRemote constructor end");
    }

    @PostConstruct
    public void init() {
        initDataView();
        initBestElementsList();
    }

    public void deleteElement(String type, Integer id) {

        if(type.equals(Category.class.getSimpleName())) {
            charactersServiceRemote.deleteCategory(id);
        } else if(type.equals(Element.class.getSimpleName())) {
            charactersServiceRemote.deleteElement(id);
        } else {
            System.out.println("Delete element: Unknown type");
        }
        initDataView();
    }

    private void addNode(TreeNode parentNode, TreeNodeData data, Collection<Element> elements) {
        TreeNode node = new DefaultTreeNode(data, parentNode);
        node.setExpanded(true);
        if (elements != null) {
            for (Element e : elements) {
                addNode(node,
                        new TreeNodeData(e.getClass().getSimpleName(), e.getIdElement(), e.getName()),
                        null
                );
            }
        }
    }

    private void initDataView() {
        root = new DefaultTreeNode(new TreeNodeData(null, null, "Categories"), null);
        this.getCategories().forEach(
                Category -> addNode(
                        root,
                        new TreeNodeData(
                                Category.getClass().getSimpleName(),
                                Category.getIdCategory(),
                                Category.getName()
                        ),
                        charactersServiceRemote.getElementsByIdCategory(Category.getIdCategory())
                ));
    }

    private void initBestElementsList() {
        bestElements = charactersServiceRemote.getBestElementsForTypeSets();
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
}
