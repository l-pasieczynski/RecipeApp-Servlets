package pl.coderslab.web.recipe;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.RecipeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "appRecipeDelete", urlPatterns = {"/app/recipe/delete"})
public class AppRecipeDelete extends HttpServlet {

    private static AdminDao userDao = new AdminDao();
    private static RecipeDao recipeDao = new RecipeDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Integer recipeId = Integer.parseInt(request.getParameter("recipeId"));
        String decision = request.getParameter("decision");
        boolean isRecipeAddedToPlan = recipeDao.checkRecipeAtPlan(recipeId);

        if (decision.equals("OK") && !isRecipeAddedToPlan){
            recipeDao.delete(recipeId);
            response.sendRedirect(request.getContextPath() + "/app/recipe/list");
        }
        else {
            response.sendRedirect(request.getContextPath() + "/app/recipe/list");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("userId");
        String name = userDao.readById(userId).getFirstName();
        request.setAttribute("name", name);
        int superId = userDao.readById(userId).getSuperAdmin();
        request.setAttribute("superId", superId);

        String recipeId = request.getParameter("recipe_id");
        request.setAttribute("recipeId", recipeId);
        getServletContext().getRequestDispatcher("/appRecipeDelete.jsp").forward(request, response);

    }
}
