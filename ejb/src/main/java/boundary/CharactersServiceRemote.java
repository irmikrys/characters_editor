package boundary;

import model.User;
import model.Wood;

import java.util.LinkedList;

public interface CharactersServiceRemote {

    String getHello();

    LinkedList<User> getAllUsers();

    LinkedList<Wood> getAllWoods();

    LinkedList<Wood> getAllWoodsWithElves();

    void addWood(String name, Integer size);
}
