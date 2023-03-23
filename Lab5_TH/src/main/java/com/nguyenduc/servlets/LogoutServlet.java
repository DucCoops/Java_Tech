package com.nguyenduc.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie ck=new Cookie("username","");
        ck.setMaxAge(0);
        response.addCookie(ck);
        ck=new Cookie("password","");
        ck.setMaxAge(0);
        response.addCookie(ck);
        response.sendRedirect("./login");
    }
}
