package bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;


@Named(value = "helloBean")
@SessionScoped
public class HelloBean implements Serializable {
    private static final long serialVersionUID = -5399245311162268519L;

    public HelloBean() {
        System.out.println("bean constructor");
    }

    @PostConstruct
    public void init() {
        System.out.println("bean PostConstruct");
    }

    public String getHello() {
        return "Hello from helloBean!";
    }

}
