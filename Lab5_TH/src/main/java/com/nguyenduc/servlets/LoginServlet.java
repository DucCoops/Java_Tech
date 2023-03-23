package com.nguyenduc.servlets;

import com.nguyenduc.dao.UserDAO;
import com.nguyenduc.model.Usertable;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    UserDAO userDAO = new UserDAO(Usertable.class);
    // This method handles the GET request to the login page
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display the login form
        request.getRequestDispatcher("/jsps/login.jsp").forward(request, response);
    }

    // This method handles the POST request when the user submits the login form
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the username and password from the form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String remember = request.getParameter("remember");
        HttpSession session = request.getSession();
        Usertable isValidUser = userDAO.isValidUser(new Usertable(username,password));
        // Check if the username and password are valid
        if (isValidUser != null) {
            // If the user selected "Remember username & password", store the login information in a cookie for 30 days
            if (remember != null) {
                Cookie usernameCookie = new Cookie("username", username);
                usernameCookie.setMaxAge(30 * 24 * 60 * 60); // 30 days
                response.addCookie(usernameCookie);

                Cookie passwordCookie = new Cookie("password", password);
                passwordCookie.setMaxAge(30 * 24 * 60 * 60); // 30 days
                response.addCookie(passwordCookie);
                // Redirect the user to the home page
                response.sendRedirect("./");
            }
            else {
                session.setAttribute("username", isValidUser.getUsername());
                response.sendRedirect("./");
            }

        } else {
            // If the username or password is invalid, display an error message using a flash message technique
            request.getSession().setAttribute("errorMessage", "Invalid username or password");
//            request.setAttribute("error", "Invalid username or password");
            response.sendRedirect("./login");
        }
    }

    // This method checks if the given username and password are valid

}
