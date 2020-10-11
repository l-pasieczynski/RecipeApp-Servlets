package pl.coderslab.web.homePage;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Register", urlPatterns = "/register")
public class Register extends HttpServlet {

    AdminDao adminDao = new AdminDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        boolean isEmailTaken = adminDao.checkEmail(email);
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");

        if (repassword.equals(password) && !isEmailTaken) {
//            Email already taken
            request.setAttribute("message", "E-mail zajęty");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        } if (!password.equals(repassword) && isEmailTaken){
            //            Password missmatch
            request.setAttribute("message", "Hasła różnią się");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        } if (repassword.equals(password) && isEmailTaken){
//            Create new user
                AdminDao userRegister = new AdminDao();
                Admin user = new Admin(name, surname, email, password, 0, 1);
                user.setHashPassword(password);
                userRegister.create(user);
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
    }
}