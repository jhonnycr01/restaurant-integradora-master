package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Restaurant implements Comparable<Restaurant> , Serializable{

	private String name; 
	private String nit;
	private String administratorName;

	public Restaurant(String name, String nit, String administratorName) {
		this.name = name;
		this.nit = nit;
		this.administratorName = administratorName;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getAdministratorName() {
		return administratorName;
	}

	public void setAdministratorName(String administratorName) {
		this.administratorName = administratorName;
	}

	@Override
	public int compareTo(Restaurant restaurant) {
		
		int comparation=0;
	
		if(this.getName().compareTo(restaurant.getName()) == -1) {
			comparation = - 1;
		}
		else if (this.getName().compareTo(restaurant.getName()) == 0) {
			comparation = 0;
		}
		else if(this.getName().compareTo(restaurant.getName()) == 1) {
			comparation = 1;
		}

		return comparation;
	}
}