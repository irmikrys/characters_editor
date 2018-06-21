package dao;

import model.Element;

import javax.ejb.Stateless;

@Stateless
public class ElementDAO extends AbstractDAO<Element, Integer> {

    public ElementDAO() {
        this.entityClass = Element.class;
    }

    public void update(Integer id, Integer fortune, Integer power) {
        findById(id).ifPresent(element -> {
            if (fortune != null)
                element.setFortune(fortune);
            if (power != null)
                element.setPower(power);
        });
    }

}
