package ubb.web.lab8.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ubb.web.lab8.controller.BoardController;
import ubb.web.lab8.controller.UserController;
import ubb.web.lab8.model.Board;
import ubb.web.lab8.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet(urlPatterns = {"/jsp/gameServlet"})
public class GameServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(GameServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        Long userId = Long.valueOf(req.getParameter("Reset"));
        Board board = BoardController.GetInstance().getByUserId(userId).get();
        for(int i = 0; i < Board.BoardSize; i++) {
            board.setTileAt(i, Board.BoardSize - 1 - i);
        }
        board.setNumberOfMoves(0);
        BoardController.GetInstance().updateBoard(board);

        HttpSession session = req.getSession(true);
        session.setAttribute("currentSessionUser", UserController.GetInstance().getUserById(userId).get());
        session.setAttribute("board", board.getTiles());
        RequestDispatcher dispatcher = req.getRequestDispatcher("game.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.setContentType("text/html;charset=UTF-8");

        int cellId = Integer.valueOf(req.getParameter("cellId"));
        int cellValue = Integer.valueOf(req.getParameter("cellVal"));
        Long userId = Long.valueOf(req.getParameter("userId"));

        Board board = BoardController.GetInstance().getByUserId(userId).get();
        int blankPosition = -1;
        for(int i = 0; i < Board.BoardSize; i++) {
            if (board.getTileAt(i) == 8) {
                blankPosition = i;
                break;
            }
        }

        switch (blankPosition) {
            case 0:
                if(cellId == 1) {
                    board = swapTiles(board, 0, 1);
                } else if (cellId == 3) {
                    board = swapTiles(board, 0, 3);
                }
                break;
            case 1:
                if(cellId == 0) {
                    board = swapTiles(board, 1, 0);
                } else if (cellId == 2) {
                    board = swapTiles(board, 1, 2);
                } else if (cellId == 4) {
                    board = swapTiles(board, 1, 4);
                }
                break;
            case 2:
                if(cellId == 1) {
                    board = swapTiles(board, 2, 1);
                } else if (cellId == 5) {
                    board = swapTiles(board, 2, 5);
                }
                break;
            case 3:
                if(cellId == 0) {
                    board = swapTiles(board, 3, 0);
                } else if (cellId == 4) {
                    board = swapTiles(board, 3, 4);
                } else if (cellId == 6) {
                    board = swapTiles(board, 3, 6);
                }
                break;
            case 4:
                if(cellId == 1) {
                    board = swapTiles(board, 4, 1);
                } else if (cellId == 3) {
                    board = swapTiles(board, 4, 3);
                } else if (cellId == 5) {
                    board = swapTiles(board, 4, 5);
                } else if (cellId == 7) {
                    board = swapTiles(board, 4, 7);
                }
                break;
            case 5:
                if(cellId == 2) {
                    board = swapTiles(board, 5, 2);
                } else if (cellId == 4) {
                    board = swapTiles(board, 5, 4);
                } else if (cellId == 8) {
                    board = swapTiles(board, 5, 8);
                }
                break;
            case 6:
                if(cellId == 3) {
                    board = swapTiles(board, 6, 3);
                } else if (cellId == 7) {
                    board = swapTiles(board, 6, 7);
                }
                break;
            case 7:
                if(cellId == 4) {
                    board = swapTiles(board, 7, 4);
                } else if (cellId == 6) {
                    board = swapTiles(board, 7, 6);
                } else if (cellId == 8) {
                    board = swapTiles(board, 7, 8);
                }
                break;
            case 8:
                if(cellId == 7) {
                    board = swapTiles(board, 8, 7);
                } else if (cellId == 5) {
                    board = swapTiles(board, 8, 5);
                }
                break;
            default:
                logger.log(Level.ERROR, "An error occured on player action. Blank position was: {}", blankPosition);
                break;
        }

        board.setNumberOfMoves(board.getNumberOfMoves() + 1);

        BoardController.GetInstance().updateBoard(board);
        logger.log(Level.INFO, "Updated board: {}", boardToString(board));


        Optional<User> userOptional = UserController.GetInstance().getUserById(userId);
        if (userOptional.isEmpty()) {
            resp.sendRedirect("invalidLogin.jsp");
        }

        User user = userOptional.get();
        HttpSession session = req.getSession(true);
        session.setAttribute("currentSessionUser", user);
        session.setAttribute("board", board.getTiles());

        //Issue: Sent Data is Undefined

//        Map<String, String> options = new LinkedHashMap<>();
//        for(int i = 0; i < Board.BoardSize; i++) {
//            options.put(String.valueOf(i), String.valueOf(board.getTileAt(i)));
//        }
//        String json = new Gson().toJson(options);
//
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");
//        resp.getWriter().write(json);


//        Cookie cookie = new Cookie("board", boardToString(board));
//        cookie.setMaxAge(60);
//        resp.addCookie(cookie);

        //req.setAttribute("board", board.getTiles());

//        Gson gson = new Gson();
//        JSONArray jsonArray = new JSONArray();
//        for(int i = 0; i < Board.BoardSize; i++) {
//            JSONObject obj = new JSONObject();
//            String str = "" + i;
//            obj.put(str, board.getTileAt(i));
//            jsonArray.add(obj);
//        }
//        resp.getWriter().write(jsonArray.toString());

        //JsonElement element = gson.toJsonTree(board);
        //resp.getWriter().write(element.toString());

        //req.getRequestDispatcher("game.jsp").forward(req, resp);
    }

    private Board swapTiles(Board board, int i, int j) {
        int tile = board.getTileAt(i);
        board.setTileAt(i, board.getTileAt(j));
        board.setTileAt(j, tile);
        return board;
    }

    private boolean isWinCondition(Board board) {
        for(int i = 0; i < Board.BoardSize; i++) {
            if (board.getTileAt(i) != i) {
                return false;
            }
        }
        return true;
    }

    private String boardToString(Board board) {
        String str = "";
        for(int i = 0; i < Board.BoardSize-1; i++) {
            str += board.getTileAt(i) + " ";
        }
        str += board.getTileAt(Board.BoardSize-1);
        return str;
    }
}
