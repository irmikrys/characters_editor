package app;

import dto.CategoryDTO;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        ClientREST clientREST = new ClientREST();

        clientREST.getAllCategories()
                .forEach(System.out::println);

        Optional<CategoryDTO> category1 = clientREST.getCategoryById(3);
        category1.ifPresent(System.out::println);

        Optional<CategoryDTO> category2 = clientREST.getCategoryById(9);
        category2.ifPresent(System.out::println);

    }
}
