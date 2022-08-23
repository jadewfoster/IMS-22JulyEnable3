package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

/**
 * Takes in order details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	/**
	 * Reads all orders to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	/**
	 * Creates an order by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please enter the customer ID you would like the order to belong to:");
		Long id = utils.getLong();
		LOGGER.info("Please enter the item ID you want to add to the order:");
		Long item_id = utils.getLong();
		LOGGER.info("Please enter the quantity of this item:");
		Integer quantity = utils.getInteger();
		Order order = orderDAO.create(new Order(id, item_id, quantity));
		LOGGER.info("Order has been created successfully!");
		return order;
	}

	/**
	 * Updates an existing item by taking in user input
	 */
	@Override
	 public Order update() {
        LOGGER.info("Please enter the order ID you would like to update:");
        long order_id = utils.getLong();
        LOGGER.info("Please enter the item ID you would like to add to the order:");
        long item_id = utils.getLong();
        LOGGER.info("Please enter the quantity of this item you would like to add:");
        int quantity = utils.getInteger();
        Order addItem = orderDAO.update(new Order(order_id, item_id, quantity));
        LOGGER.info("Order has been updated successfully!");
        return addItem;
    }

	/**
	 * Deletes an order by the id of the order
	 * Can also delete an item from an order
	 * @return
	 */
	@Override
	public int delete() {
        LOGGER.info("Please select whether you would like to delete the entire order, or an item from the order");
        LOGGER.info("DEL ORDER/DEL ITEM");
        String action = utils.getString();
        action = action.toUpperCase();
        switch (action) {
        
        
            case "DEL ORDER":
                LOGGER.info("Please enter the order id of the order you would like to delete:");
                Long order_id = utils.getLong();
                orderDAO.delete(order_id);
                LOGGER.info("Order has successfully been deleted!");
                break;
                
                
            case "DEL ITEM":
                LOGGER.info("Please enter the order id you would like to delete an item from:");
                Long order_id2 = utils.getLong();
                LOGGER.info("Please enter the item id you would like to delete from the order:");
                Long item_id = utils.getLong();
                orderDAO.itemDelete(order_id2, item_id);
                LOGGER.info("Item from order has successfully been deleted!");
                break;
                
                
            default:
                LOGGER.info("Invalid input, please try again!");
                return 0;
        }
        return 0;

    }

}
