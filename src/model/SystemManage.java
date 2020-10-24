package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class SystemManage implements Serializable{

	private String id;
	
	private ArrayList <Restaurant> restaurants;
	private ArrayList <Product> products;
	private ArrayList <Client> clients;
	private ArrayList <Order> orders;
	
	public final static String PATH_RESTAURANTS = "files/restaurants.csv";
	public final static String PATH_PRODUCTS = "files/products.csv";
	public final static String PATH_CLIENTS = "files/clients.csv";
	public final static String PATH_ORDERS = "files/orders.csv";
	
	public SystemManage(String id) {
		this.id=id;
		File rest = new File("serialized/restaurants"+this.id);
		if(rest.exists()) {
			deserializeRestaurants();
		}
		else {
                    this.restaurants =  new ArrayList<>();	
		}
		File prod = new File("serialized/products" +this.id);
		if(prod.exists()) {
			deserializeProducts();
		}
		else {
			System.out.println("inicializa products");
			this.products = new ArrayList<Product>();	
		}
		File cli = new File("serialized/clients"+this.id);
		if(cli.exists()) {
			deserializeClients();
		}
		else {
			this.clients = new ArrayList<>();	
		}
		File ord = new File("serialized/orders"+this.id);
		if(ord.exists()) {
			deserializaOrders();
		}
		else {
			this.orders = new ArrayList<>();	
		}
	}
	
	public ArrayList<Restaurant> getRestaurants() {
		return restaurants;
	}
	
	public ArrayList <Product> getProducts(){
		return products;
	}
	
	public ArrayList <Client> getClients(){
		return clients;
	}
	
	public ArrayList <Order> getOrders(){
		return orders;
	}
	
	//imprimir ordenes y productos
	/*
	 * 
	 * un metodo que devuelve todas las ordenes como un string
	 * uno igual para los productos
	 * un pa los clientes
	 * tenemos un método que serializa restaurantes
	 * que serializa restaurante productos ordenes clientes
	 * 
	 */
	
	public void addRestaurant(Restaurant restaurant){
		try{
			restaurants.add(restaurant);
			serializeRestaurants();
		}catch(NullPointerException e) {
			restaurants = new ArrayList<Restaurant>();
			restaurants.add(restaurant);
			serializeRestaurants();
		}
	}
	/*
	 * 
	 * generate report products return products
	 * save products escribe el archivo
	 * load products 
	 */
	public void addProduct(Product product) {
		try {
			this.products.add(product);
			serializeProducts();
		}catch(NullPointerException e) {
			products = new ArrayList<Product>();
			this.products.add(product);
			serializeProducts();
		}
		//metodo de reporte de productos
	
	}
	
	public void addClient(Client client) {
		try{
			clients.add(client);
			serializeClients();
		}catch(NullPointerException e) {
			clients = new ArrayList<Client>();
			clients.add(client);
			serializeClients();
		}
	}
	
	public void addOrder(Order order) {
		try{
			orders.add(order);
			serializeOrders();
		}catch(NullPointerException e) {
			orders = new ArrayList<Order>();
			orders.add(order);
			serializeOrders();
		}
	}
	
	
	public void updateRestaurant(String name, String nit, String administratorName) {
		Restaurant r= null;
		for (int i = 0; i < restaurants.size(); i++) {
			if(nit==restaurants.get(i).getNit()) {
				r = restaurants.get(i);
				r.setAdministratorName(administratorName);
				r.setName(administratorName);
				break;
			}
		}
	}
	
	public void updateOrder(Order code) {
		
		
	}
	
	public void updateProduct(String id, String name, String description, double cost, String nit) {
		Product p = null;
		for (int i = 0; i < products.size(); i++) {
			if(id==products.get(i).getCode()) {
				p = products.get(i);
				break;
			}
		}
		p.setCost(cost);
		p.setDescription(description);
		p.setName(name);
		p.setNit(nit);
		serializeProducts();
	}
	
	public void updateClient(String id, String idType, String name, String adress, String phone) {
		 Client c = null;
		 for (int i = 0; i < clients.size(); i++) {
				if(id==clients.get(i).getId()) {
				
					clients.get(i).setAdress(adress);
					clients.get(i).setIdType(idType);
					clients.get(i).setName(name);
					clients.get(i).setPhone(phone);
					serializeClients();
					break;
				}
		 }
		 
	}
	
	public void updateOrder(String orderCode, String date, String clientCode, String resturantNit) {
		Order o = null;
		for (int i = 0; i < orders.size(); i++) {
			if(orderCode==orders.get(i).getOrderCode()) {
				o = orders.get(i);
				break;
			}
		}
		o.setClientCode(clientCode);
		o.setDate(date);
		o.setResturantNit(resturantNit);
	}
	
	public boolean validateProduct(Product product) {
		boolean answer=false;
		for(int i=0; i<products.size(); i++) {
			if(products.get(i)==product) {
				answer=true;
			}
		}
		return answer;
	}
	
	public void updateOrderState(String code) {
		Order or=null;
		or = searchOrderByCode(code);
		
		if(or.getOrderState().equalsIgnoreCase("request")) {
			or.setOrderState("in progress");
		}
		else if(or.getOrderState().equalsIgnoreCase("in progress")) {
			or.setOrderState("sent");
		}
		else if(or.getOrderState().equalsIgnoreCase("sent")) {
			or.setOrderState("delivered");
		}
	}
		
	
	public Order searchOrderByCode(String code) {
		Order or=null;
		
		for (int i = 0; i < orders.size(); i++) {
			if(code.equalsIgnoreCase(orders.get(i).getOrderCode())) {
				or = orders.get(i);
				break;
			}
		}
			return or;
	}
	
	
	public void serializeRestaurants() {
		try {
			File f = new File("serialized/restaurants" +this.id);
			ObjectOutputStream fichero = new ObjectOutputStream(new FileOutputStream(f));
			fichero.writeObject(restaurants);
			//esto se puede borrar, si funciona debera imprimirse
			System.out.println("the system saved the data from the club : " + id);
			System.out.println();
			fichero.close();

		} catch (Exception e1) {
			System.out.println(e1);
			System.out.println("no se pudo serializar");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deserializeRestaurants() {

		System.out.println("loading restaurant data ....");
		try {
			File f = new File("serialized/restaurants" + this.id);

			ObjectInputStream fichero_recuperado = new ObjectInputStream(new FileInputStream(f));
			restaurants = (ArrayList<Restaurant>) fichero_recuperado.readObject();

			fichero_recuperado.close();
		} catch (Exception e) {
			System.out.println("No se pudo deserializar");
		}

	}
	
	public void serializeProducts() {
		try {
			File fileProducts = new File("serialized/products"+this.id);
			ObjectOutputStream ficheroPro = new ObjectOutputStream(new FileOutputStream(fileProducts));
			ficheroPro.writeObject(products);
			ficheroPro.close();
		} catch (IOException e) {
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deserializeProducts() {
		try {
			
			File fileProducts = new File("serialized/products" +this.id);
			ObjectInputStream ficheroPro = new ObjectInputStream(new FileInputStream(fileProducts));
			products = (ArrayList<Product>) ficheroPro.readObject();
			System.out.println("loading products data ....");
			ficheroPro.close();
		}
		catch(IOException | ClassNotFoundException cnfe) {
			
		}
	}
	
	public void serializeOrders() {
		try{
			File fileOrders = new File("serialized/orders" +this.id);
			ObjectOutputStream ficheroOrd = new ObjectOutputStream(new FileOutputStream(fileOrders));
			ficheroOrd.writeObject(orders);
			ficheroOrd.close();
		}
		catch (IOException e){
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deserializaOrders() {
		try{ 
		
			File fileOrders = new File("serialized/orders" +this.id);
			ObjectInputStream ficheroOrd = new ObjectInputStream(new FileInputStream(fileOrders));
			orders = (ArrayList<Order>) ficheroOrd.readObject();
			System.out.println("loading orders data ....");
			ficheroOrd.close();
		}
		catch(IOException | ClassNotFoundException cnfe) {
			
		}
	}
	
	public void serializeClients() {
		try {
			System.out.println("loading clients data ....");
			File fileClients = new File("serialized/clients" +this.id);
			ObjectOutputStream ficheroCli = new ObjectOutputStream(new FileOutputStream(fileClients));
			ficheroCli.writeObject(clients);
			ficheroCli.close();
		}
		catch (IOException e ){
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deserializeClients() {
		try {
			 File fileClients = new File("serialized/clients" +this.id);
			 ObjectInputStream ficheroCli = new ObjectInputStream(new FileInputStream(fileClients));
			 clients = (ArrayList<Client>) ficheroCli.readObject();
			 ficheroCli.close();
		}
		catch(IOException | ClassNotFoundException cnfe) {
			
		}
	}
		/*
		 * 
		 * un metodo que devuelve todas las ordenes como un string
		 * uno igual para los productos
		 * un pa los clientes
		 */
	
	public String generateOrderReport(String s) {
		String msg = "OrderCode" + s + "Date" + s + "ClientCode" + s
					 + "RestaurantNIT" + s + "OrderState" + "\n";
		for (int i = 0; i < orders.size(); i++) {
			msg += orders.get(i).getOrderCode() + s +  orders.get(i).getDate() + s + 
					s + orders.get(i).getClientCode() + s + orders.get(i).getResturantNit() + 
					s + orders.get(i).getOrderState() + "\n";
		}
		return msg;
	}
	
	public void saveOrders(String s) throws FileNotFoundException {
		File fileOrders = new File(PATH_ORDERS);
		PrintWriter pw = new PrintWriter(fileOrders);

		String repStr = generateOrderReport(s);
		pw.print(repStr);
		pw.close();
	}
	
	public void loadOrders(String s) throws FileNotFoundException{
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(PATH_ORDERS)));
			String line = br.readLine();

			while(line!=null) {	
				
				String[] info = line.split(s);
				
				String ordercode = info[0]; 
				String date = info[1];
				String clientcode = info[2];
				String restaurantnit = info[3];
				String orderstate = info[4];
				
				Order order = new Order(ordercode, date, clientcode, restaurantnit, orderstate, null);
				addOrder(order);
				
				line = br.readLine();
			}
			br.close();
			
		}catch(Exception e){
			
		}
	}
	
	public String generateReportProducts(String s) {
		String msg = "Code" + s + "Name" + s + "Decription" + s + "Cost" + s + "Nit" + "\n";
		for (int i = 0; i < products.size(); i++) {
			msg += products.get(i).getCode() + s +  products.get(i).getName() + s + 
					s + products.get(i).getDescription() + s + products.get(i).getCost() + 
					s + products.get(i).getNit() + "\n";
		}
		return msg;
	}
	
	public void saveProducts() throws FileNotFoundException {
		File fileProducts = new File(PATH_PRODUCTS);
		PrintWriter pw = new PrintWriter(fileProducts);

		String repStr = generateReportProducts("*");
		pw.print(repStr);
		pw.close();
	}
	
	public void loadProducts(String s) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(PATH_PRODUCTS)));
			String line = br.readLine();

			while(line!=null) {	
				
				String info[] = line.split(s);
				String code = info[0];
				String name = info[1];
				String description = info[2];
				double cost = Double.parseDouble(info[3]);
				String nit = info[4];
				
				Product product = new Product(code,name,description,cost,nit);
				addProduct(product);
				
				line = br.readLine();
			}
			br.close();
			
		}catch(Exception e){
			
		}
	}
	
	public String generateReportClients() {
		String msg = "CLIENTS:"+ "\n";
		for (int i = 0; i < clients.size(); i++) {
			
			msg += clients.get(i).getId() + "," +  clients.get(i).getIdType() + "," + 
					"," + clients.get(i).getName() + "," + clients.get(i).getAdress() + 
					"," + clients.get(i).getPhone() + "\n";
		}
		return msg;
	}
	
	public void saveClients(String s) throws FileNotFoundException {
		File fileClients = new File(PATH_CLIENTS);
		PrintWriter pw = new PrintWriter(fileClients);
		
		String repStr = generateReportClients();
		pw.print(repStr);
		pw.close();
	}
	
	public void loadClients() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(PATH_PRODUCTS)));
			String line = br.readLine();

			while(line!=null) {	
				
				String[] info = line.split(",");
				String id = info[0];
				String idtype = info[1];
				String name = info[2];
				String address = info[3];
				String phone = info[4];
				
				Client client = new Client(id,idtype,name,address,phone);
				addClient(client);
				line = br.readLine();
			}
			br.close();
			
		}catch(Exception e){
			
		}
	}
	
	public String generateReportRestaurants(String s) {
		String msg = "Nit" + s + "Name" + s + "AdministratorName" + "\n";
		for (int i = 0; i < restaurants.size(); i++) {
			
			msg += restaurants.get(i).getNit() + "," +  restaurants.get(i).getName() + "," + 
					"," + restaurants.get(i).getAdministratorName() + "\n";
		}
		return msg;
	}
	
	public void saveRestaurants(String s) throws FileNotFoundException {
		File fileRestaurants = new File(PATH_RESTAURANTS);
		PrintWriter pw = new PrintWriter(fileRestaurants);
		
		String repStr = generateReportRestaurants(s);
		pw.print(repStr);
		pw.close();
	}
	
	public void loadRestaurants(String s) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(PATH_RESTAURANTS)));
			String line = br.readLine();

			while(line!=null) {	
				
				String[] info = line.split(s);
				
				String name = info[0];
				String nit = info[1];
				String administrator = info[2];
				
				Restaurant restaurant = new Restaurant(name, nit, administrator);
				addRestaurant(restaurant);
				
				line = br.readLine();
			}
			br.close();
			
		}catch(Exception e){
			
		}
	}

	
	
	public void sortNamesByBubble() {
		for(int i=0; i< restaurants.size()-1;i++) {
			for(int j=0;j<restaurants.size()-i-1;j++) {
				if(restaurants.get(j).compareTo(restaurants.get(j+1))== 1) {
					Restaurant aux = restaurants.get(j);
					restaurants.set(j, restaurants.get(j+1));
					restaurants.set(j+1,aux);
				}
			}
		}
	}
	
	public void sortCostBySelection() {
		for(int i=0;i<products.size();i++){
			int min = i;
			for(int j= i+1;i<products.size();j++) {
				Product minvalue = products.get(min);
				Product current = products.get(j);
				if(current.getCost() < minvalue.getCost()) {
					min = j;
				}
				Product aux = products.get(min);
				products.set(min, products.get(min));
				products.set(i, aux);
			}
		}
	}

	public void addProduct(String code, String name, String description, double cost, String nit) {
		try {
		Product p1 = new Product(code, name, description, cost, nit);
		products.add(p1);
		serializeProducts();
		}catch(NullPointerException e) {
			products = new ArrayList<Product>();
			Product p1 = new Product(code, name, description, cost, nit);
			products.add(p1);
			serializeProducts();
		}
	}
}
