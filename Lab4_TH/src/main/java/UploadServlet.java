import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.JsonObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY = "uploads";
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/Upload.jsp").forward(req,resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check if the request has the multipart/form-data format
        if (!ServletFileUpload.isMultipartContent(request)) {
            JsonObject errorResponse = new JsonObject();
            errorResponse.addProperty("error", "Invalid request");
            response.getWriter().print(errorResponse);
            response.getWriter().flush();
            return;
        }

        // Configure the upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // Create upload directory if it doesn't exist
        File uploadDir = new File(getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        // Parse the request and extract the uploaded file(s)
        List<FileItem> formItems = null;
        try {
            formItems = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        if (formItems == null || formItems.size() == 0) {
            JsonObject errorResponse = new JsonObject();
            errorResponse.addProperty("error", "No file uploaded");
            response.getWriter().print(errorResponse);
            response.getWriter().flush();
            return;
        }

        String userFileName = null;
        String override = "false";

        // Process the uploaded file(s)
        for (FileItem item : formItems) {
            if(item.isFormField())
            {
                String fieldName = item.getFieldName();
                String value = item.getString();
                if (fieldName.equals("filename")) {
                    userFileName = value;
                }
                if (fieldName.equals("override")) {
                    override = value;
                }
            }
        }
        for (FileItem item: formItems) {
            if(!item.isFormField())
            {
                String fFileName = new File(item.getName()).getName();
                String [] strings = fFileName.split("\\.");
                String fileSuport = "txt, doc, docx, img, pdf, rar, zip";
                if (!fileSuport.contains(strings[1])) {
                    JsonObject errorResponse = new JsonObject();
                    errorResponse.addProperty("error", "Unsupported file extension:" + strings[1]);
                    response.getWriter().print(errorResponse);
                    response.getWriter().flush();
                    return;
                }
                String fileName = userFileName+"."+strings[1];
                String filePath = uploadDir + File.separator + fileName;
                File storeFile = new File(filePath);
                System.out.println(userFileName);
                System.out.println(fileName);

                // Check if the file already exists and the "override if exists" checkbox is not selected

                if (storeFile.exists() && !override.equals("true")) {
                    JsonObject errorResponse = new JsonObject();
                    errorResponse.addProperty("error", "File already exists");
                    response.getWriter().print(errorResponse);
                    response.getWriter().flush();
                    return;
                }else if(storeFile.exists() && override.equals("true")) {
                    JsonObject errorResponse = new JsonObject();
                    errorResponse.addProperty("sussces", "File has been overriden");
                    response.getWriter().print(errorResponse);
                    response.getWriter().flush();
                }

                // Save the file
                try {
                    item.write(storeFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                response.setContentType("text/html");
                String downloadLink = request.getContextPath() + "/" + UPLOAD_DIRECTORY + "/" + fileName;
                response.getWriter().write("<html><body><h3>File uploaded. \n" +
                        "Click here to visite file. Click <a href=\"" + downloadLink + "\">here</a> to download file.</h3></body></html>");

            }
        }
    }
}
