package pl.coderslab.web.recipe;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.AddPlan;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "appRecipePlanAdd", urlPatterns = "/app/recipe/plan/add")
public class AppRecipePlanAdd extends HttpServlet {
    private PlanDao planDao = new PlanDao();
    private AdminDao adminDao = new AdminDao();
    private RecipeDao recipeDao = new RecipeDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        int id = (int) session.getAttribute("userId");
        int planId = Integer.parseInt(request.getParameter("planId"));
        int recipeId = Integer.parseInt(request.getParameter("recipeId"));
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int dayNameId = Integer.parseInt(request.getParameter("dayNameId"));
        String mealName = request.getParameter("name");
        AddPlan plan = new AddPlan(recipeId, orderId, dayNameId, planId, mealName);
        recipeDao.add(plan);
        response.sendRedirect(request.getContextPath() + "/app/recipe/plan/add");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("userId");
        List<Plan> plans = planDao.findAll(userId);
        List<Recipe> recipies = recipeDao.findAll();
        String name = adminDao.readById(userId).getFirstName();
        int superId = adminDao.readById(userId).getSuperAdmin();
        request.setAttribute("superId", superId);
        request.setAttribute("plans", plans);
        request.setAttribute("recipies", recipies);

        request.setAttribute("name", name);
        getServletContext().getRequestDispatcher("/appRecipePlanAdd.jsp").forward(request, response);
    }
}
