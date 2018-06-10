package boundary;

import model.User;

import java.util.LinkedList;

public interface CharactersServiceRemote {

    String getHello();

    LinkedList<User> getAllUsers();
}
