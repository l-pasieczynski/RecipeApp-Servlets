package pl.coderslab.web.admin;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AppUserEditData", urlPatterns = {"/app/user/edit"})
public class AppUserEditData extends HttpServlet {

    private static AdminDao AdminDao = new AdminDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");


        Admin admin = AdminDao.readById(userId);
        admin.setFirstName(firstName);
        admin.setLastName(lastName);
        admin.setEmail(email);

        AdminDao.update(admin);
        response.sendRedirect(request.getContextPath() + "/app/dashboard");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("userId");
        Admin user = AdminDao.readById(userId);
        String name = user.getFirstName();
        request.setAttribute("name", name);
        request.setAttribute("user", user);
        int superId = user.getSuperAdmin();
        request.setAttribute("superId", superId);
        getServletContext().getRequestDispatcher("/appUserEditData.jsp").forward(request, response);

    }
}
