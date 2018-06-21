package bean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "managerBean")
@SessionScoped
public class ManagerBean implements Serializable {

    private static final long serialVersionUID = -7902579108607730057L;

    ManagerBean() {

    }
}
