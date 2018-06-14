package bean;

import boundary.CharactersServiceRemote;
import model.Elf;
import model.TreeNodeData;
import model.Wood;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import util.EJBUtility;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Named(value = "catalogBean")
@SessionScoped
public class CatalogBean implements Serializable {

    private static final long serialVersionUID = -9217712787886869451L;

    private CharactersServiceRemote charactersServiceRemote;
    private TreeNode root;

    public CatalogBean() throws NamingException {

        charactersServiceRemote = EJBUtility.lookupCharactersService();
        System.out.println("Catalog charactersServiceRemote constructor end");
    }

    @PostConstruct
    public void init() {
        initDataView();
    }

    public void deleteElement(String type, Integer id) {

        System.out.println("Deleting something...");
        if(type.equals(Wood.class.getSimpleName())) {
            charactersServiceRemote.deleteWood(id);
        } else if(type.equals(Elf.class.getSimpleName())) {
            charactersServiceRemote.deleteElf(id);
        } else {
            System.out.println("Delete element: Unknown type");
        }
        initDataView();
    }

    private void addNode(TreeNode parentNode, TreeNodeData data, Collection<Elf> elements) {
        TreeNode node = new DefaultTreeNode(data, parentNode);
        node.setExpanded(true);
        if (elements != null) {
            for (Elf e : elements) {
                addNode(node,
                        new TreeNodeData(e.getClass().getSimpleName(), e.getIdElf(), e.getName()),
                        null
                );
            }
        }
    }

    public TreeNode getRoot() {
        return root;
    }

    private void initDataView() {
        root = new DefaultTreeNode(new TreeNodeData(null, null, "Categories"), null);
        this.getWoods().forEach(
                wood -> addNode(
                        root,
                        new TreeNodeData(wood.getClass().getSimpleName(), wood.getIdWood(), wood.getName()),
                        wood.getElfByIdWood()
                ));
    }

    private List<Wood> getWoods() {
        return new LinkedList<>(charactersServiceRemote.getAllWoods());
    }
}
