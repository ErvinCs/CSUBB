package servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ubb.exam.controller.UserController;
import ubb.exam.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = {"/loginServlet"})
public class LoginServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserController userController = UserController.GetInstance();
        Optional<User> userOptional = userController.getUserByCredentials(username, password);
        if (userOptional.isEmpty()) {
            resp.sendRedirect("jsp/invalidLogin.jsp");
        } else {
            User user = userOptional.get();
            HttpSession session = req.getSession(true);
            session.setAttribute("currentSessionUser", user);
            resp.sendRedirect("jsp/menu.jsp");
        }
    }
}
