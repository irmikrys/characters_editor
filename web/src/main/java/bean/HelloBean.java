package bean;

import boundary.CharactersServiceRemote;
import model.Wood;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;


@Named(value = "helloBean")
@SessionScoped
public class HelloBean implements Serializable {
    private static final long serialVersionUID = -5399245311162268519L;

    private CharactersServiceRemote bean;

    public HelloBean() {
        System.out.println("bean constructor");
    }

    @PostConstruct
    public void init() throws NamingException {
        System.out.println("bean PostConstruct");
        final Properties jndiProperties = new Properties();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        Context context = new InitialContext(jndiProperties);

        bean = (CharactersServiceRemote) context.lookup(
                "ejb:/web_main_war/CharactersService!boundary.CharactersServiceRemote"
        );
        getWoods();
        System.out.println("bean PostConstruct end");
    }

    public String getHello() {
        return "Hello from helloBean!";
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
