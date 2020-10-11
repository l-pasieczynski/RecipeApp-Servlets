package pl.coderslab.web.admin;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;
import pl.coderslab.utils.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AppSuperAdmin", urlPatterns = {"/app/admin/users"})
public class AppSuperAdminUsers extends HttpServlet {

    private static AdminDao userDao = new AdminDao();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int userId = Integer.parseInt(request.getParameter("userId"));

        Admin user = userDao.readById(userId);
        if (user.getEnable() == 1){
            user.setEnable(0);
            userDao.update(user);
            request.setAttribute("message", "Użytkownik zablokowany");
            response.sendRedirect(request.getContextPath() + "/app/admin/users");
        } else if (user.getEnable() == 0){
            user.setEnable(1);
            userDao.update(user);
            request.setAttribute("message", "Użytkownik aktywny");
            response.sendRedirect(request.getContextPath() + "/app/admin/users");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        HttpSession session = request.getSession();
//        Admin admin = (Admin) session.getAttribute("admin");
//        String firstName = admin.getFirstName();
//        int isSuperAdmin = admin.getSuperAdmin();


        Session.adminSession(request);


        AdminDao allAdmin = new AdminDao();
        List<Admin> allUsers = allAdmin.findAll();
        request.setAttribute("allUsers", allUsers);

        getServletContext().getRequestDispatcher("/appSuperAdminUsers.jsp").forward(request, response);

    }
}
