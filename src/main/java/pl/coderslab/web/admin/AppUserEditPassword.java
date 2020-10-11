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

@WebServlet(name = "AppUserEditPassword", urlPatterns = {"/app/user/edit/password"})
public class AppUserEditPassword extends HttpServlet {

    private static AdminDao userDao = new AdminDao();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int UserId = (int) session.getAttribute("userId");

        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");

        if (password.equals(repassword)) {
            Admin user = userDao.readById(UserId);
            user.setHashPassword(password);
            userDao.update(user);
            response.sendRedirect(request.getContextPath() + "/app/dashboard");
        } else {
            request.setAttribute("message", "Hasła różnią się");
            response.sendRedirect(request.getContextPath() + "/app/user/edit/password");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Session.adminSession(request);
        getServletContext().getRequestDispatcher("/appUserEditPassword.jsp").forward(request, response);

    }
}
