package ubb.exam.servlets;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ubb.exam.controller.ItemController;
import ubb.exam.controller.UserController;
import ubb.exam.model.Item;
import ubb.exam.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/jsp/detailsItemServlet/*"})
public class DetailsItemServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(DetailsItemServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        ItemController itemCon = ItemController.GetInstance();
        UserController userCon = UserController.GetInstance();
        Long itemId = Long.parseLong(req.getParameter("itemId"));
        try {
            Item item = itemCon.getItemById(itemId).get();
            User user = userCon.getUserById(item.getUserId()).get();

            req.setAttribute("currentItem", item);
            req.setAttribute("userOwner", user);
            RequestDispatcher dispatcher = req.getRequestDispatcher("viewDetailsItem.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String cmd = req.getParameter("operation");
        cmd = cmd.trim();
        logger.log(Level.INFO, "Operation: " + cmd);

        Long itemId = Long.parseLong(req.getParameter("itemId"));
        logger.log(Level.INFO, "ItemId: " + itemId);

        ItemController itemCon = ItemController.GetInstance();

        if (cmd.equals("update")) {
            logger.log(Level.INFO, "Updating");
            Item item = itemCon.getItemById(itemId).get();

            String name = req.getParameter("name");
            String description = req.getParameter("description");
            Integer value = Integer.parseInt(req.getParameter("value"));

            item.setName(name);
            item.setDescription(description);
            item.setValue(value);

            itemCon.updateItem(item);
        } else if (cmd.equals("delete")) {
            logger.log(Level.INFO, "Deleting");
            itemCon.deleteItem(itemId);
        }
    }
}
