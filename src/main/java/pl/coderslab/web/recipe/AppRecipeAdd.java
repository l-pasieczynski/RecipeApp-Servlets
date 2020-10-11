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

@WebServlet(name = "appRecipeAdd", urlPatterns = "/app/recipe/add")
public class AppRecipeAdd extends HttpServlet {

    private static AdminDao aDao = new AdminDao();
    private static RecipeDao rDao = new RecipeDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        int id = (int) session.getAttribute("userId");

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int time = Integer.parseInt(request.getParameter("time"));
        String descHow = request.getParameter("descHow");
        String ingredients = request.getParameter("ingredients");
        Recipe recipe = new Recipe(name, ingredients, description, LocalDateTime.now(), LocalDateTime.now(), time, descHow, id);
        rDao.create(recipe);
        response.sendRedirect(request.getContextPath() + "/app/recipe/list");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("userId");
        String name = aDao.readById(userId).getFirstName();
        request.setAttribute("name", name);
        int superId = aDao.readById(userId).getSuperAdmin();
        request.setAttribute("superId", superId);
        getServletContext().getRequestDispatcher("/appRecipeAdd.jsp").forward(request, response);

    }
}
