package ubb.exam.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ubb.exam.model.Item;
import ubb.exam.model.ItemUser;
import ubb.exam.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

public class ItemController {
    private static final Logger logger = LogManager.getLogger(ItemController.class);
    private ItemRepository items;
    private static ItemController Controller = new ItemController();

    public static ItemController GetInstance() {
        return Controller;
    }

    private ItemController() {
        this.items = new ItemRepository();
    }


    public List<Item> getItems() {
        logger.log(Level.INFO, "Get Items: {}", this.items);
        return items.getAll();
    }

    public Optional<Item> getItemById(Long id) {
        logger.log(Level.INFO, "Get Item By Id: {}", id);
        return this.items.getById(id);
    }

    public List<Item> getItemsByUser(Long id) {
        logger.log(Level.INFO, "Get Items by User: {}", id);
        return this.items.getItemsByUser(id);
    }

    public List<Item> getItemsPaged(int page) {
        logger.log(Level.INFO, "Get Items Paged: {}", page);
        return this.items.getItemsPaged(page);
    }

    public List<ItemUser> getNoItemsPerUser() {
        logger.log(Level.INFO, "Get Items Per User");
        return this.items.getNoItemsPerUser();
    }


    public void addItem(Item item) {
        logger.log(Level.INFO, "Add Item: {}", item);
        this.items.add(item);
    }

    public void addItems(List<Item> items) {
        logger.log(Level.INFO, "Add Multiple Items: {}", items);
        this.items.addMultiple(items);
    }


    public void deleteItem(Long id) {
        logger.log(Level.INFO, "Delete Item By Id: {}", id);
        this.items.delete(id);
    }

    public void deleteItems(List<Item> items) {
        logger.log(Level.INFO, "Delete Multiple Items: {}", items);
        this.items.deleteMultiple(items);
    }


    public void updateItem(Item item) {
        logger.log(Level.INFO, "Update Item: {}", item);
        this.items.update(item);
    }

    public void updateItems(List<Item> items) {
        logger.log(Level.INFO, "Update Multiple Items: {}", items);
        this.items.updateMultiple(items);
    }


}
