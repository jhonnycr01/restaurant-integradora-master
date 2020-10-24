package ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.*;

public class Menu {

	private static  SystemManage s1;
	private Scanner sc;

	public Menu() {
		s1 = new SystemManage("12");
		sc = new Scanner(System.in);
		showWelcomeMsg();
		systemHandler();

	}

	// Method that displays a welcome message for the user
	public void showWelcomeMsg() {

		String msg = "";

		msg += "******************************************************************\n";
		msg += "****************** RESTAURANT MANAGING SYSTEM **********************\n";
		msg += "*************** developed by: Jhonny Cataño *****************\n";
		msg += "*****************************************************************\n";
		msg += "******************************************************************\n";

		// mensaje += mostrarBannerSeparacion();

		System.out.println();
		System.out.println(msg);
	}


	public void systemHandler() {

		boolean exit = false;

		while (!exit) {

			int userInput = systemMenu();

			switch (userInput) {
			case 1:
				registerRestaurant();

				break;

			case 2:
				addProduct();
				break;

			case 3:

				addCliente();

				break;

			case 4:

				addOrder();
				break;

			case 5:

				System.out.println(s1.generateReportRestaurants("-"));
				break;
				
			case 6:
				//LoadClients();
				
				/*
				 * System.out.println("6.show products");
				System.out.println("7.show clients");
				System.out.println("8.show orders");
				System.out.println("9.save information");
				System.out.println("10.exit");
				 */
				System.out.println(s1.generateReportProducts("-"));
				break;
				
			case 7:
				System.out.println(s1.generateReportClients());
				//loadOrders();
				break;
				
			case 8:
				System.out.println(s1.generateOrderReport("-"));
				break;
				
			case 9: 
				// generateReportClients();
				 break;
				 
			case 10:
				exit = true;
				break;
				
			case 11:
			//	generateOrderReport();
				break;
				 
			case 12:
				//generateReportRestaurants();
				break;
				
			case 13:
				exit = true;
			//	save();
				break;

		

			default:
				System.out.println();

			}

		}
//	catch(InputMismatchException e) {
//		System.out.println("please type a number");
//		continue;
//	}
		System.out.println();
		System.out.println("******************************************************************");
		System.out.println("***************** Thanks for using the program *******************");
		System.out.println("******************************************************************");
	}
	
	
	

	private void saveInformation() {
		s1.serializeClients();
		s1.serializeOrders();
		s1.serializeProducts();
		s1.serializeRestaurants();
		
	}

	public int systemMenu() throws InputMismatchException {
		int value;
		while (!false) {
			try {
				System.out.println("What would you like to do?");

				System.out.println("1.add restaurant");
				System.out.println("2.add product ");
				System.out.println("3.add client ");
				System.out.println("4.add order");
				System.out.println("5.show restaurants");
				System.out.println("6.show products");
				System.out.println("7.show clients");
				System.out.println("8.show orders");
				System.out.println("9.save information");
				System.out.println("10.exit");
				

				value = Integer.parseInt(sc.nextLine());
//				sc.nextLine();
			} catch (InputMismatchException e) {
				continue;
			}

			return value;
		}
	}

	public static void registerRestaurant() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Name:");
		String name =sc.nextLine();
		System.out.println("Nit:");
		String nit =sc.nextLine();
		System.out.println("AdminName:");
		String admin = sc.nextLine();
		Restaurant r1 = new Restaurant(name, nit, admin);
		s1.addRestaurant(r1);
		s1.serializeRestaurants();
		
	}
        
        public static void addProduct() {
		//aqui se agrega un nuevo producto
        Scanner sc = new Scanner(System.in);
        System.out.println("code:");
		String code = sc.nextLine();
		System.out.println("name:");
		String name = sc.nextLine();
		System.out.println("description:");
		String description = sc.nextLine();
		System.out.println("cost:");
		double cost = Double.parseDouble(sc.nextLine());
		System.out.println("nit:");
		String nit = sc.nextLine();
		
		Product p1 = new Product(code, name, description, cost, nit);
		s1.addProduct(code, name, description, cost, nit);
	}
        
        public static void addOrder() {
		//aqui se agrega un nuevo producto
        Scanner sc = new Scanner(System.in);
		System.out.println("order code:");
		String orderCode = sc.nextLine();
		System.out.println("date:");
		String date = sc.nextLine();
		System.out.println("client code:");
		String clientCode = sc.nextLine();
		System.out.println("restaurant nit:");
		String nit = sc.nextLine();
		System.out.println("order state:");
		String orderState = sc.nextLine();
		Order o1 = new Order(orderCode, date, clientCode, nit, orderState, null);
		s1.addOrder(o1);
	}
         public static void addCliente() {
		//aqui se agrega un nuevo producto
        Scanner sc = new Scanner(System.in);
        System.out.println("id:");
        String id = sc.nextLine();
        System.out.println("id type:");
        String idType = sc.nextLine();
		System.out.println("name:");
		 String name = sc.nextLine();
		System.out.println("direccion:");
		 String adress= sc.nextLine();
		System.out.println("telefono:");
		String phone = sc.nextLine();
		Client c1 = new Client(id, idType, name, adress, phone);
		s1.addClient(c1);

	}
        public static ArrayList<Client> ordenar(ArrayList<Client> clientes) {
		
                //Algoritmo de Burbuja
                Client aux;
                int intercambios = 0;
                int comparaciones = 0;
                for(int x = 1; x < clientes.size(); x++)
                {
                    for(int y = 0; y < clientes.size()-1; y++)
                    {
                        comparaciones++;
                        int cs1,cs2;
                        cs1 = (int)clientes.get(x-1).getName().charAt(0);
                        cs2 = (int)clientes.get(x).getName().charAt(0);
                        if( cs1 < cs2 )
                        {
                            aux = clientes.get(x-1);
                            clientes.set(x-1,clientes.get(x));
                            clientes.set(x,aux);
                            intercambios++;
                        }
                    }
                }
            return clientes;
        }
}
