package pl.coderslab.web.recipe;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "appRecipesList", urlPatterns = {"/app/recipe"})
public class AppRecipesList extends HttpServlet {
    RecipeDao recipeDao = new RecipeDao();
    AdminDao aDao = new AdminDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("userId");
        String name = aDao.readById(userId).getFirstName();
        List<Recipe> recipe = recipeDao.findAllById(userId);
        int superId = aDao.readById(userId).getSuperAdmin();
        request.setAttribute("superId", superId);
        request.setAttribute("recipe", recipe);
        request.setAttribute("name", name);
        getServletContext().getRequestDispatcher("/appRecipesList.jsp").forward(request, response);

    }
}
