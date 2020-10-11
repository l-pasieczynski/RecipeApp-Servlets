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
import java.time.LocalDateTime;

@WebServlet(name = "AppRecipeEdit", urlPatterns = {"/app/recipe/edit"})
public class AppRecipeEdit extends HttpServlet {

    private static AdminDao userDao = new AdminDao();
    private static RecipeDao recipeDao = new RecipeDao();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int UserId = (int) session.getAttribute("userId");

        String name = request.getParameter("name");
        int recipeId = Integer.parseInt(request.getParameter("recipeId"));
        String description = request.getParameter("description");
        int time = Integer.parseInt(request.getParameter("time"));
        String preparation = request.getParameter("preparation");
        String ingredients = request.getParameter("ingredients");

        Recipe recipe = new Recipe(recipeId, name, ingredients, description, LocalDateTime.now(), LocalDateTime.now(), time, preparation, UserId);
        recipeDao.update(recipe);
        response.sendRedirect(request.getContextPath() + "/app/recipe/list");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("userId");
        String name = userDao.readById(userId).getFirstName();
        request.setAttribute("name", name);

        int superId = userDao.readById(userId).getSuperAdmin();
        request.setAttribute("superId", superId);

        int recipeId = Integer.parseInt(request.getParameter("recipe_id"));
        Recipe recipe = recipeDao.read(recipeId);
        request.setAttribute("recipe", recipe);
        getServletContext().getRequestDispatcher("/appRecipeEdit.jsp").forward(request, response);

    }
}
