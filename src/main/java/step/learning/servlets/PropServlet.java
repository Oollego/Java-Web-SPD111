package step.learning.servlets;

import com.google.inject.Singleton;
import step.learning.dal.dao.PropDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import step.learning.dal.dao.PropDao;

@Singleton
public class PropServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PropDao propDao = new PropDao();
        req.setAttribute("Prop", propDao.GetPropItems());

        req.setAttribute("page-body", "prop");
        req.getRequestDispatcher("/WEB-INF/_layout.jsp").forward(req, resp);
    }
}
