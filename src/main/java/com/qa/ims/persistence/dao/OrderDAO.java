package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {

    public static final Logger LOGGER = LogManager.getLogger();
    private static final DecimalFormat df = new DecimalFormat("0.00");
    
    @Override
    public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
        Long order_id = resultSet.getLong("order_id");
        Long id = resultSet.getLong("fk_customer_id");
        return new Order(order_id, id);
    }

    /**
     * Reads all orders from the database
     * 
     * @return A list of orders
     */
    @Override
    public List<Order> readAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DBUtils.getInstance().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                		"SELECT * FROM orders o"
                        + " INNER JOIN orders_items oi"
                        + " ON o.order_id = oi.fk_order_id"
                        + " INNER JOIN items i"
                        + " ON i.item_id = fk_item_id"
                        + " INNER JOIN customers c"
                        + " ON o.fk_customer_id = c.id");) {
            while (resultSet.next()) {
                orders.add(modelFromResultSet(resultSet));
                LOGGER.info(
                		"\n	Order ID: " + resultSet.getLong("o.order_id") + 
                		"\n	Customer Name: " + resultSet.getString("c.first_name") + " " + resultSet.getString("c.surname") +  
                        "\n	Item Name: " + resultSet.getString("i.item_name") + 
                        "\n	Price: " + resultSet.getDouble("i.item_price") + 
                        "\n	Quantity: " + resultSet.getInt("oi.quantity") + "\n");
            }
        } catch (SQLException e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }

        
        // Then to calculate total cost
        try (Connection connection = DBUtils.getInstance().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        "SELECT order_id, SUM(quantity*item_price) as TotalPrice"
                        		+ " FROM orders o"
                                + " INNER JOIN orders_items oi"
                                + " ON o.order_id = oi.fk_order_id"
                                + " INNER JOIN items i"
                                + " ON i.item_id = fk_item_id"
                                + " INNER JOIN customers c"
                                + " ON o.fk_customer_id = c.id"
                                + " GROUP BY order_id;");){
            while (resultSet.next()) {
                orders.add(total(resultSet));
                LOGGER.info("	Order ID: "+ resultSet.getLong("order_id") + " -> Total price: £" + df.format(resultSet.getDouble("TotalPrice")) );
            }
        } catch (SQLException e) {
        	LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }
    
    public Order readLatest() {
        try (Connection connection = DBUtils.getInstance().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY order_id DESC LIMIT 1");) {
        		resultSet.next();
            return modelFromResultSet(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
		return null;
    }

    
    /**
     * Creates an order in the database
     * 
     * @param order - takes in a order object. id will be ignored
     */
    
    
    @Override
    public Order create(Order order) {
        try (Connection connection = DBUtils.getInstance().getConnection();
                PreparedStatement statement = connection
                        .prepareStatement(
                                "INSERT INTO orders(fk_customer_id) VALUES (?)" +
                        		"SELECT order_id INTO @newid FROM orders WHERE fk_customer_id = ? " +
                                "ORDER BY order_id DESC LIMIT 1;" +
                                "INSERT INTO orders_items(fk_order_id, fk_item_id, quantity) VALUES (@newid, ?, ?);");) {
        	statement.setLong(1, order.getId());
        	statement.setLong(2, order.getId());
        	statement.setLong(3, order.getItem_id());
            statement.setInt(4, order.getQuantity());
            statement.executeUpdate();
            LOGGER.info(readLatest());
            return order;
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return order;
    }

    @Override
    public Order read(Long id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
                PreparedStatement statement = connection
                        .prepareStatement(
                "SELECT * FROM orders o" + 
                "INNER JOIN orders_items oi" + 
                "ON o.order_id=oi.fk_order_id" + 
                "INNER JOIN items i" + 
                "ON i.item_id=fk_item_id" +
                "WHERE order_id = (?)");) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery();) {
                resultSet.next();
                return modelFromResultSet(resultSet);
            }
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }
    
    /**
     * Calculates total order price
     */
    public Order total(ResultSet resultSet) throws SQLException {
        double totalPrice= resultSet.getDouble("TotalPrice");
        return new Order(totalPrice);
    }
    
    /**
     * Updates an order in the database
     * 
     * @param order - takes in an order object, the id field will be used to
     *                 update that item in the database
     * @return
     */
    @Override
    public Order update(Order order) {
        try (Connection connection = DBUtils.getInstance().getConnection();
                PreparedStatement statement2 = connection
                        .prepareStatement(
                                "INSERT INTO orders_items(fk_order_id, fk_item_id, quantity) VALUES (?, ?, ?)");) {
            statement2.setLong(1, order.getOrder_id());
        	statement2.setLong(2, order.getItem_id());
            statement2.setInt(3, order.getQuantity());
            statement2.executeUpdate();
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }
    
    /**
     * Deletes an order in the both orders and orders_customers
     * 
     * @param id - id of the order
     */
    @Override
    public int delete(long order_id) {

        try (Connection connection = DBUtils.getInstance().getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("DELETE FROM orders_items WHERE fk_order_id = ?");) {
            statement.setLong(1, order_id);
            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        try (Connection connection = DBUtils.getInstance().getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("DELETE FROM orders WHERE order_id = ?");) {
            statement.setLong(1, order_id);
            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }

        return 0;
    }

    /**
     * Deletes an item in an order
     * 
     * @param order_id - id of the order in which the item is to be deleted
     * item_id - id of the item to be deleted in that order
     */
    public int itemDelete(Long order_id, Long item_id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
                PreparedStatement statement = connection
                        .prepareStatement(
                                "DELETE FROM orders_items WHERE fk_order_id = ? AND fk_item_id = ? LIMIT 1");) {
            statement.setLong(1, order_id);
            statement.setLong(2, item_id);
            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }
}
