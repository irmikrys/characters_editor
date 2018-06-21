package app;

import dao.CategoryDAO;
import dto.CategoryDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Path("/editor")
public class EditorManager {

    @Inject
    private CategoryDAO categoryDAO;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/categories")
    public Response getAllCategories() {
        List<CategoryDTO> categoryDTOS = categoryDAO.findAll()
                .stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toList());
        return Response.status(Response.Status.OK)
                .entity(categoryDTOS)
                .build();
    }
}