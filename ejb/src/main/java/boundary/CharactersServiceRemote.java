package boundary;

import model.Category;
import model.Element;
import model.User;

import java.util.LinkedList;

public interface CharactersServiceRemote {

    String getHello();

    LinkedList<User> getAllUsers();

    LinkedList<Category> getAllCategories();

    LinkedList<Category> getAllCategoriesWithElements();

    Category getCategoryByIdCategory(Integer idCategory);

    void addCategory(String name, Integer size);

    void updateCategory(Integer idCategory, String name, Integer size);

    void deleteCategory(Integer id);

    Element getElementByIdElement(Integer idElement);

    Element getElementWithCategoryByIdElement(Integer idElement);

    void addElement(Category category, String name, Integer quantity, Integer propType, Integer power);

    void updateElement(Integer idElement, String name, Integer fortune, Integer propType, Integer power);

    void deleteElement(Integer id);
}
