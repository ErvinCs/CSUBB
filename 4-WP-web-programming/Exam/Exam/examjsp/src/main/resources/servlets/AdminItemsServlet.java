package servlets;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ubb.exam.controller.ItemController;
import ubb.exam.model.Item;
import ubb.exam.model.ItemUser;
import ubb.exam.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/jsp/adminItemsServlet"})
public class AdminItemsServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AdminItemsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        ItemController itemCon = ItemController.GetInstance();

        List<ItemUser> itemList = new ArrayList<>(itemCon.getNoItemsPerUser());
        Gson itemListData = new Gson();
        String data = itemListData.toJson(itemList);

        resp.getWriter().write(data);
    }
}
