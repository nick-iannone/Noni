package com.techelevator.domain;

public class Food {
	
	private String productCode;
	private String price;
	private String itemName;
	private String type;
	private int quantity;
	private int quantityInCart;
	
	public Food(String productCode, String itemName, String price, String type, int quantity) {
		this.productCode = productCode;
		this.itemName = itemName;
		this.price = price;
		this.type = type;
		this.quantity = 50;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProductCode() {
		return productCode;
	}

	public String getPrice() {
		return price;
	}

	public String getItemName() {
		return itemName;
	}

	public String getType() {
		return type;
	}
	
	public int getQuantityInCart() {
		return quantityInCart;
	}

	public void setQuantityInCart(int quantityInCart) {
		this.quantityInCart = quantityInCart;
	}
}
