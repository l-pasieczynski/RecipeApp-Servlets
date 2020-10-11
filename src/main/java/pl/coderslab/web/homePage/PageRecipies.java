package pl.coderslab.web.homePage;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "PageRecipies", urlPatterns = {"/recipies"})
public class PageRecipies extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String search = request.getParameter("search");
        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> searchedRecipe = recipeDao.findAllBySearchedRecipeName(search);
        request.setAttribute("recipies", searchedRecipe);
        getServletContext().getRequestDispatcher("/PageRecipies.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> recipes = recipeDao.findAll();
        Collections.reverse(recipes);
        request.setAttribute("recipies", recipes);
        getServletContext().getRequestDispatcher("/PageRecipies.jsp").forward(request, response);

    }
}
