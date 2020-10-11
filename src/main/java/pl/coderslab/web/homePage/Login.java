package pl.coderslab.web.homePage;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();


        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AdminDao adminDao = new AdminDao();
        Admin admin = adminDao.readByMail(email);

        if (admin != null) {

            boolean isPasswordValid = adminDao.checkPassword(email, password);

            int enable = admin.getEnable();
            if (enable == 0) {
                request.setAttribute("message", "Zostałeś zablokowany");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }

            if (isPasswordValid) {
                int maxLoginTime‬ = 3600;
                session.setAttribute("isLogged", 1);
                session.setAttribute("admin", admin);
                session.setAttribute("userId", admin.getId());
                session.setMaxInactiveInterval(maxLoginTime‬);

                response.sendRedirect(request.getContextPath() + "/app/dashboard");

            } else {
                request.setAttribute("message", "Błedny użytkownik lub hasło.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }
        if (admin == null) {
            request.setAttribute("message", "Nie jesteś zarejestrowany");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);

    }
}
