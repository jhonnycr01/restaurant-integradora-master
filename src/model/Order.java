package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable{

	private String orderCode;
	private String date;
	private String clientCode;
	private String resturantNit;
	private String orderState;
	private ArrayList <String> productList;
	
	public Order(String orderCode, String date, String clientCode, String resturantNit, 
			String orderState, ArrayList<String> productList) {
		this.orderCode = orderCode;
		this.date = date;
		this.clientCode = clientCode;
		this.resturantNit = resturantNit;
		this.productList = productList;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getResturantNit() {
		return resturantNit;
	}

	public void setResturantNit(String resturantNit) {
		this.resturantNit = resturantNit;
	}

	public ArrayList<String> getProductList() {
		return productList;
	}
	
	public void addProductToList(String product) {
		productList.add(product);
	}

	public void setProductList(ArrayList<String> productList) {
		this.productList = productList;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
}