package pl.coderslab.web.plan;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "appPlanDelete", urlPatterns = {"/app/plan/delete"})
public class AppPlanDelete extends HttpServlet {

    private static AdminDao userDao = new AdminDao();
    private static PlanDao planDao = new PlanDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Integer recipeId = Integer.parseInt(request.getParameter("planId"));
        String decision = request.getParameter("decision");

        if (decision.equals("OK")){
            planDao.delete(recipeId);
            response.sendRedirect(request.getContextPath() + "/app/plan/list");
        }
        else {
            response.sendRedirect(request.getContextPath() + "/app/plan/list");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("userId");
        String name = userDao.readById(userId).getFirstName();
        int superId = userDao.readById(userId).getSuperAdmin();
        request.setAttribute("superId", superId);
        request.setAttribute("name", name);

        String planId = request.getParameter("plan_id");
        request.setAttribute("planId", planId);
        getServletContext().getRequestDispatcher("/appPlanDelete.jsp").forward(request, response);

    }
}
