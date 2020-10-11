package pl.coderslab.web.recipe;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "RecipeList", urlPatterns = {"/app/recipe/list"})
public class AppRecipe extends HttpServlet {

    private static RecipeDao recipeDao = new RecipeDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Session.adminSession(request);
        int userId = Session.adminSession(request).getId();

        List<Recipe> adminRecipies = recipeDao.findAllById(userId);
        request.setAttribute("adminRecipies", adminRecipies);
        getServletContext().getRequestDispatcher("/appRecipe.jsp").forward(request, response);

    }
}
