package bean;

import boundary.CharactersServiceRemote;
import model.Elf;
import model.Wood;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

@Named(value = "catalogBean")
@SessionScoped
public class CatalogBean implements Serializable {
    private static final long serialVersionUID = -9217712787886869451L;

    private CharactersServiceRemote bean;
    private TreeNode root;

    public CatalogBean() throws NamingException {
        final Properties jndiProperties = new Properties();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        Context context = new InitialContext(jndiProperties);

        bean = (CharactersServiceRemote) context.lookup(
                "ejb:/web_main_war/CharactersService!boundary.CharactersServiceRemote"
        );
        List<Wood> woods = getWoods();
        root = new DefaultTreeNode("Woods", null);
        woods.forEach(wood -> addNode(root, wood.getName(), wood.getElfByIdWood()));
        System.out.println("Catalog bean constructor end");
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
        return new LinkedList<>(bean.getAllWoods());
    }
}
