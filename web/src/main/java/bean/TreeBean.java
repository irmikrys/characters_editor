package bean;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "treeBasicView")
@ViewScoped
public class TreeBean implements Serializable {

    private static final long serialVersionUID = -4408190977299037587L;
    private TreeNode root;

    @PostConstruct
    public void init() {
        root = new DefaultTreeNode("Root", null);
        TreeNode node0 = new DefaultTreeNode("Node 0", root);
        TreeNode node1 = new DefaultTreeNode("Node 1", root);

        TreeNode node00 = new DefaultTreeNode("Node 0.0", node0);
        TreeNode node01 = new DefaultTreeNode("Node 0.1", node0);

        TreeNode node10 = new DefaultTreeNode("Node 1.0", node1);

        node1.getChildren().add(new DefaultTreeNode("Node 1.1"));
        node00.getChildren().add(new DefaultTreeNode("Node 0.0.0"));
        node00.getChildren().add(new DefaultTreeNode("Node 0.0.1"));
        node01.getChildren().add(new DefaultTreeNode("Node 0.1.0"));
        node10.getChildren().add(new DefaultTreeNode("Node 1.0.0"));
        root.getChildren().add(new DefaultTreeNode("Node 2"));
    }

    public TreeNode getRoot() {
        return root;
    }
}
