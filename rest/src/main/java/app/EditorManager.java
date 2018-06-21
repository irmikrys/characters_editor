package app;

import dao.CategoryDAO;
import dao.ElementDAO;
import dto.CategoryDTO;
import dto.ElementDTO;
import model.Category;
import model.Element;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
@Path("/editor")
public class EditorManager {

    @Inject
    private CategoryDAO categoryDAO;

    @Inject
    private ElementDAO elementDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/categories")
    public Response getAllCategories() {
        List<CategoryDTO> categoryDTOS = categoryDAO.findAll()
                .stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toList());
        return Response
                .status(Response.Status.OK)
                .entity(categoryDTOS)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/categories/{idCategory}")
    public Response getCategoryById(@PathParam("idCategory") Integer idCategory) {
        Optional<Category> category = categoryDAO.findById(idCategory);
        if (category.isPresent()) {
            return Response
                    .status(Response.Status.OK)
                    .entity(new CategoryDTO(category.get()))
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/categories/{idCategory}/elements")
    public Response addElement(@PathParam("idCategory") Integer idCategory,
                               ElementDTO elementDTO) {
        Optional<Category> category = categoryDAO.findById(idCategory);
        if (category.isPresent()) {
            Element element = new Element(elementDTO, category.get());
            elementDAO.add(element);
            return Response
                    .ok()
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .header("Not Found", "category by id " + idCategory)
                    .build();
        }
    }
}
