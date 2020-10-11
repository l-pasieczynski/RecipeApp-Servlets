package pl.coderslab.utils;

import pl.coderslab.model.Admin;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Session extends HttpServlet {

    public static Admin adminSession(HttpServletRequest request){

        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        String firstName = admin.getFirstName();
        int isSuperAdmin = admin.getSuperAdmin();
        request.setAttribute("name", firstName);
        request.setAttribute("superId", isSuperAdmin);
        return admin;

    }

}
