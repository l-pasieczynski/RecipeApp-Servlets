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

@WebServlet(name = "appPlanAdd", urlPatterns = "/app/plan/add")
public class AppPlanAdd extends HttpServlet {

    private static AdminDao adminDao = new AdminDao();
    private static PlanDao planDao = new PlanDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        int id = (int) session.getAttribute("userId");

        String name = request.getParameter("planName");
        String description = request.getParameter("planDescription");
        Plan plan = new Plan(name, description, LocalDateTime.now(), id);
        planDao.create(plan);
        response.sendRedirect(request.getContextPath() + "/app/plan/list");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("userId");
        String name = adminDao.readById(userId).getFirstName();
        int superId = adminDao.readById(userId).getSuperAdmin();
        request.setAttribute("superId", superId);
        request.setAttribute("name", name);
        getServletContext().getRequestDispatcher("/appPlanAdd.jsp").forward(request, response);

    }
}
