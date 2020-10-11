package pl.coderslab.web.plan;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;
import pl.coderslab.model.PlanDetails;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "appPlanDetails", urlPatterns = "/app/plan/details")
public class AppPlanDetails extends HttpServlet {
    private PlanDao pDao = new PlanDao();
    private AdminDao aDao = new AdminDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int planId = Integer.parseInt(request.getParameter("plan_id"));
        Plan plan = pDao.read(planId);
        List<PlanDetails> details = pDao.readPlanDetails(planId);
        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("userId");
        String name = aDao.readById(userId).getFirstName();
        int superId = aDao.readById(userId).getSuperAdmin();
        request.setAttribute("superId", superId);
        List<PlanDetails> planDetailsMonday = detailsDayList(1, details);
        List<PlanDetails> planDetailsTuesday = detailsDayList(2, details);
        List<PlanDetails> planDetailsWednesday = detailsDayList(3, details);
        List<PlanDetails> planDetailsThursday = detailsDayList(4, details);
        List<PlanDetails> planDetailsFriday = detailsDayList(5, details);
        List<PlanDetails> planDetailsSaturday = detailsDayList(6, details);
        List<PlanDetails> planDetailsSunday = detailsDayList(7, details);
        if (planDetailsMonday.size() != 0) {
            request.setAttribute("d1", planDetailsMonday);
        }
        if (planDetailsTuesday.size() != 0) {
            request.setAttribute("d2", planDetailsTuesday);
        }
        if (planDetailsWednesday.size() != 0) {
            request.setAttribute("d3", planDetailsWednesday);
        }
        if (planDetailsThursday.size() != 0) {
            request.setAttribute("d4", planDetailsThursday);
        }
        if (planDetailsFriday.size() != 0) {
            request.setAttribute("d5", planDetailsFriday);
        }
        if (planDetailsSaturday.size() != 0) {
            request.setAttribute("d6", planDetailsSaturday);
        }
        if (planDetailsSunday.size() != 0) {
            request.setAttribute("d7", planDetailsSunday);
        }

        request.setAttribute("plan", plan);
        request.setAttribute("plan_id", planId);
        request.setAttribute("name", name);

        getServletContext().getRequestDispatcher("/appPlanDetails.jsp").forward(request, response);
    }

    private List<PlanDetails> detailsDayList(int a, List<PlanDetails> list) {
        List<PlanDetails> planDetailDay = new ArrayList<>();
        for (PlanDetails detail : list
        ) {
            if (detail.getDayNameId() == a) {
                planDetailDay.add(detail);
            }
        }
        return planDetailDay;
    }
}
