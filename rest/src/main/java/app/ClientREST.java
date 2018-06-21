package app;

import dto.CategoryDTO;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

class ClientREST {

    private static final String REST_URI = "http://localhost:8080/rest_main_war/rest";
    private static final String MAIN_PATH = "editor/categories";
    private Client client = ClientBuilder.newClient();

    List<CategoryDTO> getAllCategories() {
        Response response = client
                .target(REST_URI)
                .path(MAIN_PATH)
                .request(MediaType.APPLICATION_JSON)
                .get();
        System.out.println("Get all categories status: " + response.getStatus());
        return response
                .readEntity(new GenericType<List<CategoryDTO>>() {
                });
    }

    Optional<CategoryDTO> getCategoryById(Integer idCategory) {
        Response response = client
                .target(REST_URI)
                .path(MAIN_PATH)
                .path(String.valueOf(idCategory))
                .request(MediaType.APPLICATION_JSON)
                .get();
        System.out.println("Get category by id status: " + response.getStatus());
        return Optional.ofNullable(response
                .readEntity(CategoryDTO.class));
    }
}
