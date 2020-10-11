package pl.coderslab.web.homePage;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "PageRecipiesDetails", urlPatterns = {"/recipe/details"})
public class PageRecipiesDetails extends HttpServlet {

    private static RecipeDao recipies = new RecipeDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int recipeId = Integer.parseInt(request.getParameter("recipe_id"));
        Recipe recipe = recipies.read(recipeId);
        request.setAttribute("recipe", recipe);
        getServletContext().getRequestDispatcher("/pageRecipeDetails.jsp").forward(request, response);

    }
}
