import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "LoginServlet",urlPatterns = {""})
public class LoginServlet extends HttpServlet {
    private HashMap<String, String> accounts = new HashMap<String, String>();
    @Override
    public void init() throws ServletException {
        accounts.put("admin", "admin");
        accounts.put("nguyenduc", "4903");
        System.out.println("Server is running");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String username =req.getParameter("username");
        String password = req.getParameter("password");
        try {
            if (accounts.get(username).equals(password)) {
                System.out.println(username + "/" + password +" Login successful");

                // Change urlPatterns if want to redirect to others
                resp.sendRedirect("./ImageServlet");
            }else {
                System.out.println(username + "/" + password +" Login failed");
            }
        }
        catch (NullPointerException e){
            System.out.println(username + "/" + password +" Login failed");
        }
    }
}
