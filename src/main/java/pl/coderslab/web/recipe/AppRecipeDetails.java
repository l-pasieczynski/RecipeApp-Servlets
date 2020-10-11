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

@WebServlet(name = "appRecipeDetails", urlPatterns = "/app/recipe/details")
public class AppRecipeDetails extends HttpServlet {
    private RecipeDao rDao = new RecipeDao();
    private AdminDao aDao = new AdminDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recipeId = Integer.parseInt(request.getParameter("recipe_id"));
        Recipe recipe = rDao.read(recipeId);
        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("userId");
        String name = aDao.readById(userId).getFirstName();
        request.setAttribute("recipe", recipe);
        request.setAttribute("name", name);
        int superId = aDao.readById(userId).getSuperAdmin();
        request.setAttribute("superId", superId);

        getServletContext().getRequestDispatcher("/appRecipeDetails.jsp").forward(request, response);
    }
}
