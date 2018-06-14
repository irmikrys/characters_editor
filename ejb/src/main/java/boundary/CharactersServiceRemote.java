package boundary;

import model.User;
import model.Wood;

import java.util.LinkedList;

public interface CharactersServiceRemote {

    String getHello();

    LinkedList<User> getAllUsers();

    LinkedList<Wood> getAllWoods();

    LinkedList<Wood> getAllWoodsWithElves();

    Wood getWoodByIdWood(Integer idWood);

    void addWood(String name, Integer size);

    void addElf(Wood wood, String name, Integer quantity, Integer propType, Integer power);
}
