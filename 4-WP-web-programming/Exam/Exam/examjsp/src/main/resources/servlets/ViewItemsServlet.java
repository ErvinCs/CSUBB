package servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ubb.exam.controller.ItemController;
import ubb.exam.model.Item;
import ubb.exam.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

@WebServlet(urlPatterns = {"/jsp/viewItemsServlet"})
public class ViewItemsServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ViewItemsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        HttpSession session = req.getSession(true);
        ItemController itemController = ItemController.GetInstance();
        User user = (User)session.getAttribute("currentSessionUser");

        // Get Items By User
        List<Item> itemList = new ArrayList<>(itemController.getItemsByUser(user.getId()));
        Gson itemListData = new Gson();
        String data = itemListData.toJson(itemList);

        resp.getWriter().write(data);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        HttpSession session = req.getSession(true);
        User user = (User)session.getAttribute("currentSessionUser");

        // Add Item
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        int value = Integer.valueOf(req.getParameter("value"));

        ItemController itemCon = ItemController.GetInstance();
        itemCon.addItem(new Item(name, description, value, user.getId()));
    }
}
