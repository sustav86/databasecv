package ua.sustav.databasecv.web;

import ua.sustav.databasecv.model.Resume;
import ua.sustav.databasecv.storage.XmlStorage;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by SUSTAVOV on 20.09.2017.
 */
public class ResumeServlet extends HttpServlet {
    public static XmlStorage storage = new XmlStorage("C:\\Users\\SUSTAVOV\\Documents\\myProject\\databasecv\\file_storage");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html; charset=UTF-8");
//        Writer writer = response.getWriter();
//        String name = request.getParameter("name");
//        writer.write("I'm servlet " + name);
//        writer.close();

        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        Resume resume = Resume.EMPTY;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("list");
                break;
            case "create":
                resume = Resume.EMPTY;
                break;
            case "view":
            case "edit":
                resume = storage.load(uuid);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + "don't resolve");
        }

        request.setAttribute("resume", resume);
        request.getRequestDispatcher("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp").forward(request, response);
    }
}
