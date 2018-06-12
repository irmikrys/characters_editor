package bean;

import boundary.CharactersServiceRemote;
import model.Elf;
import model.Wood;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import util.EJBUtility;

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

        root = new DefaultTreeNode("Woods", null);
        this.getWoods().forEach(wood -> addNode(root, wood.getName(), wood.getElfByIdWood()));
        System.out.println("Catalog charactersServiceRemote constructor end");
    }

    private void addNode(TreeNode parentNode, String nodeName, Collection<Elf> children) {
        TreeNode node = new DefaultTreeNode(nodeName, parentNode);
        node.setExpanded(true);
        if (children != null) {
            for (Elf e : children) {
                addNode(node, e.getName(), null);
            }
        }
    }

    public TreeNode getRoot() {
        return root;
    }

    private List<Wood> getWoods() {
        return new LinkedList<>(charactersServiceRemote.getAllWoods());
    }
}
