package pl.coderslab.web.plan;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "AppPlanEdit", urlPatterns = {"/app/plan/edit"})
public class AppPlanEdit extends HttpServlet {

    private static AdminDao userDao = new AdminDao();
    private static PlanDao planDao = new PlanDao();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

        int planId = Integer.parseInt(request.getParameter("planId"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Plan plan = new Plan(planId, name, description, LocalDateTime.now(), userId);
        planDao.update(plan);
        response.sendRedirect(request.getContextPath() + "/app/plan/list");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("userId");
        String name = userDao.readById(userId).getFirstName();
        request.setAttribute("name", name);
        int superId = userDao.readById(userId).getSuperAdmin();
        request.setAttribute("superId", superId);

        int planId = Integer.parseInt(request.getParameter("plan_id"));
        Plan plan = planDao.read(planId);
        request.setAttribute("plan", plan);
        getServletContext().getRequestDispatcher("/appPlanEdit.jsp").forward(request, response);

    }
}
