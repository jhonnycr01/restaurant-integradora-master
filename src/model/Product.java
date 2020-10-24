package model;

import java.io.Serializable;

public class Product implements Serializable{

	private String code;
	private String name;
	private String description;
	private double cost;
	private String nit;
	
	public Product(String code, String name, String description, double cost, String nit) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.nit = nit;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}
}