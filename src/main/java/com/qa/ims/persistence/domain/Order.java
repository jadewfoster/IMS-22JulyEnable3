package com.qa.ims.persistence.domain;

import java.util.List;
import java.util.Objects;

public class Order {
	
    private Long order_id;
    private double totalPrice;
    private Long id;
    private int quantity;
    private Long item_id;
    private List<Item> items;

    public Order() {
    }

    public Order(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Order(Long id, Long item_id, int quantity) {
        this.id = id;
        this.quantity = quantity;
        this.item_id = item_id;

    }

    public Order(Long order_id, Long id) {
        this.order_id = order_id;
        this.id = id;

    }
    

	public Order(int quantity, Long order_id, Long item_id) {
        this.order_id = order_id;
        this.quantity = quantity;
        this.item_id = item_id;
    }

    public Order(Long order_id, double totalPrice, Long id, int quantity, Long item_id) {
        this.order_id = order_id;
        this.totalPrice = totalPrice;
        this.id = id;
        this.quantity = quantity;
        this.item_id = item_id;
    }

    public Order(Long order_id, Long id, List<Item> items) {
        this.order_id = order_id;
        this.id = id;
        this.items = items;
    }

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Long getItem_id() {
		return item_id;
	}

	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, item_id, items, order_id, quantity, totalPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id) && Objects.equals(item_id, other.item_id)
				&& Objects.equals(items, other.items) && Objects.equals(order_id, other.order_id)
				&& quantity == other.quantity
				&& Double.doubleToLongBits(totalPrice) == Double.doubleToLongBits(other.totalPrice);
	}

	@Override
	public String toString() {
		return "\n	Order ID: " + order_id + "\n	Total Price: " + totalPrice + "\n	Customer ID: " + id + "\n	Quantity: " + quantity
				+ "\n	Item ID: " + item_id + "\n	Items: " + items + "\n";
	}




}