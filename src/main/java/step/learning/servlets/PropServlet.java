package step.learning.servlets;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import step.learning.dal.dao.ProductDao;
import step.learning.services.db.DbService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class PropServlet extends HttpServlet {
    private final ProductDao productDao;

    @Inject
    public PropServlet(ProductDao productDao) {
        this.productDao = productDao;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("Prop", productDao.GetPropItems());

        req.setAttribute("page-body", "prop");
        req.getRequestDispatcher("/WEB-INF/_layout.jsp").forward(req, resp);
    }
}
