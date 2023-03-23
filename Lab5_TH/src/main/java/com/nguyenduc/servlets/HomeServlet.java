package com.nguyenduc.servlets;


import com.nguyenduc.dao.ProductDAO;
import com.nguyenduc.dao.UserDAO;
import com.nguyenduc.model.Product;
import com.nguyenduc.model.Usertable;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(urlPatterns = {""})
public class HomeServlet extends HttpServlet {

    UserDAO userDAO = new UserDAO(Usertable.class);
    ProductDAO productDAO = new ProductDAO(Product.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the user is authenticated
        if (isAuthenticated(request) || request.getSession().getAttribute("username") != null) {
            List<Product> products = productDAO.getAll();
            request.setAttribute("products",products);
            String action = request.getParameter("action");
            if (action != null) {
                if (action.equals("edit")) {
                    if (request.getParameter("id") != null) {
                        Product product = productDAO.get(Integer.parseInt(request.getParameter("id")));
                        request.setAttribute("product",product);
                    }
                }else if (action.equals("delete")) {
                    if (request.getParameter("id") != null) {
                        productDAO.remove(new Product(Integer.parseInt(request.getParameter("id") )));
                        response.sendRedirect("./");
                        return;
                    }
                }
            }
            request.getRequestDispatcher("/jsps/index.jsp").forward(request, response);
        } else {
            response.sendRedirect("./login");
        }
    }

    // This method checks if the user is authenticated
    private boolean isAuthenticated(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String username = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    request.getSession().setAttribute("username",cookie.getValue());
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            this.doPut(req,resp);
            return;
        }
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        HttpSession session = req.getSession();

        try {
            if (productDAO.add(new Product(name,Float.parseFloat(price)))) {
                session.setAttribute("msg_success", "Thêm thành công");
                resp.sendRedirect("./");
            }
            else {
                session.setAttribute("msg_error", "Có lỗi xảy ra");
                resp.sendRedirect("./");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            session.setAttribute("msg_error", "Có lỗi xảy ra");
            resp.sendRedirect("./");
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        HttpSession session = req.getSession();

        try {
            if (productDAO.update(new Product(Integer.parseInt(id),name,Float.parseFloat(price)))) {
                session.setAttribute("msg_success", "Sửa thành công");
                resp.sendRedirect("./");
            }
            else {
                session.setAttribute("msg_error", "Có lỗi xảy ra");
                resp.sendRedirect("./");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            session.setAttribute("msg_error", "Có lỗi xảy ra");
            resp.sendRedirect("./");
        }
    }
}
