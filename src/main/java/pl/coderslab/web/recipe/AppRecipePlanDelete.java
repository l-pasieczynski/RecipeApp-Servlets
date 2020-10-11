package pl.coderslab.web.recipe;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.RecipePlanDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "appRecipePlanDelete", urlPatterns = {"/app/recipe/plan/delete"})
public class AppRecipePlanDelete extends HttpServlet {

    private static AdminDao adminDao = new AdminDao();
    private static RecipePlanDao recipePlanDao = new RecipePlanDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer recipePlanId = Integer.parseInt(request.getParameter("recipePlanId"));
        Integer planId = Integer.parseInt(request.getParameter("planId"));


        String decision = request.getParameter("decision");

        if (decision.equals("OK")) {
            recipePlanDao.recipePlanDelete(recipePlanId);
            response.sendRedirect(request.getContextPath() + "/app/plan/details?plan_id=" + planId);
        } else {
            response.sendRedirect(request.getContextPath() + "/app/plan/details?plan_id=" + planId);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("userId");
        String name = adminDao.readById(userId).getFirstName();
        request.setAttribute("name", name);
        Integer recipePlanId = Integer.parseInt(request.getParameter("recipeplanid"));
        Integer planId = Integer.parseInt(request.getParameter("planid"));

        request.setAttribute("recipePlanId", recipePlanId);
        request.setAttribute("planId", planId);
        getServletContext().getRequestDispatcher("/appRecipePlanDelete.jsp").forward(request, response);

    }
}
