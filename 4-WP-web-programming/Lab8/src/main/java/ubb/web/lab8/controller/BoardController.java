package ubb.web.lab8.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ubb.web.lab8.model.Board;
import ubb.web.lab8.repository.BoardRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoardController {
    private static final Logger logger = LogManager.getLogger(BoardController.class);

    private BoardRepository boards;
    private static BoardController Controller = new BoardController();

    private BoardController() { this.boards = new BoardRepository();}

    public static BoardController GetInstance() { return Controller;}

    public List<Board> getBoards() {
        logger.log(Level.INFO, "Get Boards: {}", this.boards);
        try {
            return boards.getAll();
        } catch (IOException ex) {
            logger.log(Level.ERROR, "Could not retrieve boards: " + ex.toString());
        }
        return new ArrayList<>();
    }

    public void addBoard(Board board) {
        logger.log(Level.INFO, "Add Board: {}", board);
        try {
            this.boards.add(board);
        } catch (IOException ex) {
            logger.log(Level.ERROR, "Could not add board: " + ex.toString());
        }
    }

    public void deleteBoard(Long id) {
        logger.log(Level.INFO, "Delete Board (By Id): {}", id);
        this.boards.delete(id);
    }

    public void updateBoard(Board board) {
        logger.log(Level.INFO, "Update Board: {}", board);
        try {
            this.boards.update(board);
        } catch (IOException ex) {
            logger.log(Level.ERROR, "Could not update board: " + ex.toString());
        }
    }

    public Optional<Board> getBoardById(Long id) {
        logger.log(Level.INFO, "Get Board By Id: {}", id);
        try {
            return this.boards.getById(id);
        } catch (IOException ex) {
            logger.log(Level.ERROR, "Could not retrieve board: " + ex.toString());
        }
        return Optional.empty();
    }

    public Optional<Board> getByUserId(Long userId) {
        logger.log(Level.INFO, "Get Board By User Id: {}", userId);
        try {
            return this.boards.getByUserId(userId);
        } catch (IOException ex) {
            logger.log(Level.ERROR, "Could not retrieve board: " + ex.toString());
        }
        return Optional.empty();
    }


}
