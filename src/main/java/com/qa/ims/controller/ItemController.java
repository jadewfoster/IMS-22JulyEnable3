package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

/**
 * Takes in item details for CRUD functionality
 *
 */
public class ItemController implements CrudController<Item> {

	public static final Logger LOGGER = LogManager.getLogger();

	private ItemDAO itemDAO;
	private Utils utils;

	public ItemController(ItemDAO itemDAO, Utils utils) {
		super();
		this.itemDAO = itemDAO;
		this.utils = utils;
	}

	/**
	 * Reads all items to the logger
	 */
	@Override
	public List<Item> readAll() {
		List<Item> items = itemDAO.readAll();
		LOGGER.info("--------------------------------------------------------------------------------------------------");
		for (Item item : items) {
			LOGGER.info(item);
		}
		return items;
	}

	/**
	 * Creates an item by taking in user input
	 */
	@Override
	public Item create() {
		LOGGER.info("--------------------------------------------------------------------------------------------------\n"
				+ "Please enter an item name:");
		String itemName = utils.getString();
		LOGGER.info("Please enter an item price:");
		double price = utils.getDouble();
		LOGGER.info("Please enter the stock of the item");
		Integer stock = utils.getInteger();
		Item item = itemDAO.create(new Item(itemName, price, stock));
		LOGGER.info("------------------------------Item has been created successfully.------------------------------");
		LOGGER.info(itemDAO.readLatest());
		return item;
	}

	/**
	 * Updates an existing item by taking in user input
	 */
	@Override
	public Item update() {
		LOGGER.info("--------------------------------------------------------------------------------------------------\n"
				+ "Please enter the id of the item you would like to update:");
		Long item_id = utils.getLong();
		LOGGER.info("Please enter an item name:");
		String itemName = utils.getString();
		LOGGER.info("Please enter an item price:");
		double price = utils.getDouble();
		LOGGER.info("Please enter the stock of the item");
		Integer stock = utils.getInteger();;
		Item item = itemDAO.update(new Item(item_id, itemName, price, stock));
		LOGGER.info("------------------------------Item has been updated successfully.------------------------------");
		LOGGER.info("The new entry is: \n" + itemDAO.read(item_id));
		return item;
	}

	/**
	 * Deletes an existing item by the id of the item
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("--------------------------------------------------------------------------------------------------\n"
				+ "Please enter the id of the item you would like to delete:");
		Long item_id = utils.getLong();
		LOGGER.info("------------------------------Item has been deleted successfully.------------------------------");
		return itemDAO.delete(item_id);
	}

}
