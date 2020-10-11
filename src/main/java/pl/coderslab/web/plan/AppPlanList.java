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
import java.util.List;

@WebServlet(name = "appPlanList", urlPatterns = {"/app/plan/list"})
public class AppPlanList extends HttpServlet {
    PlanDao planDao = new PlanDao();
    AdminDao aDao = new AdminDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("userId");
        String name = aDao.readById(userId).getFirstName();
        List<Plan> plans = planDao.findAll(userId);
        request.setAttribute("plans", plans);
        request.setAttribute("name", name);
        int superId = aDao.readById(userId).getSuperAdmin();
        request.setAttribute("superId", superId);
        getServletContext().getRequestDispatcher("/appPlanList.jsp").forward(request, response);

    }
}
