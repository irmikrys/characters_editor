package bean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;


@Named(value = "helloBean")
@SessionScoped
public class HelloBean implements Serializable {
    private static final long serialVersionUID = -5399245311162268519L;

    public HelloBean() {
        System.out.println("Hello bean constructor");
    }

    public String getHello() {
        return "Hello from helloBean!";
    }


}
