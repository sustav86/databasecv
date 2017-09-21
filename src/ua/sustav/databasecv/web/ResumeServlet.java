package ua.sustav.databasecv.web;

import com.sun.org.apache.regexp.internal.RE;
import ua.sustav.databasecv.DataBaseCVConfig;
import ua.sustav.databasecv.model.ContactType;
import ua.sustav.databasecv.model.Resume;
import ua.sustav.databasecv.storage.IStorage;
import ua.sustav.databasecv.storage.XmlStorage;
import ua.sustav.databasecv.util.Util;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by SUSTAVOV on 20.09.2017.
 */
public class ResumeServlet extends HttpServlet {
    private IStorage storage;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String name = request.getParameter("name");
        String location = request.getParameter("location");
        Resume resume = Util.isEmpty(uuid) ? new Resume(name, location) : storage.load(uuid);

        resume.setFullName(name);
        resume.setLocation(location);
        resume.setHomePage(request.getParameter("home_page"));

        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value == null || value.isEmpty()) {
                resume.removeContact(type);
            } else {
                resume.addContact(type, value);
            }

        }

        if (Util.isEmpty(uuid)) {
            storage.save(resume);
        } else {
            storage.update(resume);
        }

        response.sendRedirect("list");
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

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = DataBaseCVConfig.get().getStorage();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        storage = DataBaseCVConfig.get().getStorage();
    }
}
