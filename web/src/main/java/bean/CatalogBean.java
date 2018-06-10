package bean;

import boundary.CharactersServiceRemote;
import model.Wood;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

@Named(value = "catalogBean")
@SessionScoped
public class CatalogBean implements Serializable {
    private static final long serialVersionUID = -9217712787886869451L;

    private CharactersServiceRemote bean;

    public CatalogBean() throws NamingException {
        final Properties jndiProperties = new Properties();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        Context context = new InitialContext(jndiProperties);

        bean = (CharactersServiceRemote) context.lookup(
                "ejb:/web_main_war/CharactersService!boundary.CharactersServiceRemote"
        );
        getWoods();
        System.out.println("Catalog bean constructor end");
    }

    public List getWoods() {
        List<Wood> woods = new LinkedList<>(bean.getAllWoods());
        woods.forEach(w -> {
            System.out.println(w.getName());
            w.getElfByIdWood().forEach(e -> System.out.println(e.getName()));
        });
        return new LinkedList<>(bean.getAllWoods());
    }
}
