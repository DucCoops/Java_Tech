import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/download"})
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String file_name = req.getParameter("file");
        String file_path = getServletContext().getRealPath("/" + "assets" + File.separator + file_name);
         System.out.println(file_path);

        File file = new File(file_path);
        if (file.exists()) {
            resp.setContentType("application/octet-stream");
            resp.setContentLength((int) file.length());
            resp.setHeader("Content-Disposition", "attachment; filename=\""
                    + file.getName() + "\"");

            FileInputStream fileInputStream = new FileInputStream(file);

            int i;
            while ((i = fileInputStream.read()) != -1) {
                out.write(i);
            }
            fileInputStream.close();
            out.close();

        }else{
            out.println("File not found");
        }


    }
}
