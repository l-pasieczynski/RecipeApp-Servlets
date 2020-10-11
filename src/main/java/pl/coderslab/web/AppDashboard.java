package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.LastPlan;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AppDashboard", urlPatterns = {"/app/dashboard"})
public class AppDashboard extends HttpServlet {

    private static AdminDao adminDao = new AdminDao();
    private static RecipeDao recipeDao = new RecipeDao();
    private static PlanDao planDao = new PlanDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        HttpSession session = request.getSession();
//        int userId = (int) session.getAttribute("userId");

//        Admin admin = adminDao.readById(userId);
//        int superId = admin.getSuperAdmin();
//        request.setAttribute("superId", superId);
//        String name = admin.getFirstName();
//        request.setAttribute("name", name);

        Session.adminSession(request);
        int userId = Session.adminSession(request).getId();


        Integer userRecipes = recipeDao.count(userId);
        request.setAttribute("userRecipes", userRecipes);

        Integer userPlans = planDao.count(userId);
        request.setAttribute("userPlans", userPlans);

        String planName = planDao.lastAddedPlanName(userId).getName();
        request.setAttribute("planName", planName);

        List<LastPlan> lastAddedPlan = planDao.lastAddedPlan(userId);
        request.setAttribute("userLastAddedPlan", lastAddedPlan);

        List<String> dayName = planDao.dayNameList();
        request.setAttribute("dayName", dayName);

        List<Recipe> recipes = recipeDao.findAll();
        request.setAttribute("recipies", recipes);


        getServletContext().getRequestDispatcher("/appDashboard.jsp").forward(request, response);

    }
}
