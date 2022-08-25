package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Item {

	private Long item_id;
	private String itemName;
	private double price;
	private int stock;

	public Item(String itemName, double price, int stock) {
		this.setItemName(itemName);
		this.setPrice(price);
		this.setStock(stock);
	}
	
	public Item(Long item_id, String itemName, double price, int stock) {
		this.setItem_id(item_id);
		this.setItemName(itemName);
		this.setPrice(price);
		this.setStock(stock);
	}

	public Item(long item_id){
		this.setItem_id(item_id);
	}

	public Long getItem_id() {
		return item_id;
	}

	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "\n	Item ID: " + item_id + "\n	Item Name: " + itemName + "\n	Price: " + price + "\n	Stock: " + stock + "\n";
	}

	@Override
	public int hashCode() {
		return Objects.hash(itemName, item_id, price, stock);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(itemName, other.itemName) && Objects.equals(item_id, other.item_id)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price) && stock == other.stock;
	}
	
	

	
	
	
	
}
