import java.util.*;
import java.text.*;
import java.io.*;
public class userInterface{
	private static userInterface UI;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static final int EXIT = 0;
	private static final int ADD_SUPPLIER = 1;
	private static final int EDIT_SUPPLIER = 2;
	private static final int PRINT_SUPPLIER = 3;//number for commands here
	private static final int ADD_CLIENT = 4;
	private static final int EDIT_CLIENT = 5;
	private static final int PRINT_CLIENT = 6;
	private static final int ADD_PRODUCT = 7;
	private static final int EDIT_PRODUCT_NAME = 8;
	private static final int EDIT_PRODUCT_QUANTITY = 9;
	private static final int EDIT_PRODUCT_SALE = 10;
	private static final int EDIT_PRODUCT_SUPPLY = 11;
	private static final int PRINT_PRODUCT = 12;
	private static final int SHOW_WAITLIST = 13;
	private static final int PLACE_ORDER = 14;
	private static final int SHOW_INVOICE = 15;
	private static final int ASSIGN_REL = 16;
	private static final int PRODUCT_REL = 17;
	private static final int SUPPLIER_REL = 18;
	

	private static wareHouse warehouse = new wareHouse();
		
	private userInterface() {
		warehouse = wareHouse.instance();
	}
	public static userInterface instance() {
		if(UI == null) {
			return UI = new userInterface();
		}else {
			return UI;
		}
	}
	
	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if(tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			}catch(IOException ioe) {
				System.exit(0);
			}
		}while(true);
	}
	
	
	
	public int getCommand() {
		do {
			try {
				int value = Integer.parseInt(getToken("Enter command: "));
				if(value >= EXIT) {
					return value;
				}
			}catch(NumberFormatException nfe) {
				System.out.println("Enter a number");
			}
		}while(true);
	}
	
	
	//Functions here
	
	//Supplier Class
	public void addSupplier() {
		String name = getToken("Enter supplier name: ");
		supplier temp = warehouse.addSupplier(name);
		System.out.println("Supplier added: " + temp.getName() + " " + temp.getId());
	}
	
	public void editSupplier() {
		String name = getToken("Enter new supplier name: ");
		String iD = getToken("Enter new supplier ID: ");
		
		supplier tempEdit;
		tempEdit = warehouse.editSupplier(name, iD);
		if(tempEdit == null) {
			System.out.println("Invalid supplier ID");
		}
		else {
			System.out.println("Supplier name changed: " + tempEdit);
		}
		
	}
	
	public void printSupplier() {
		warehouse.printSupplier();
	}
	
	
	//Client Class
	public void addClient() {
		String name = getToken("Enter client name: ");
		client tempAdd;
		tempAdd = warehouse.addClient(name);
		System.out.println("Client added: " + tempAdd.getName() + " " + tempAdd.getId());
	}
	
	public void editClient() {
		String name = getToken("Enter new client name: ");
		String iD = getToken("Enter new client ID: ");
		
		client result;
		result = warehouse.editClient(name, iD);
		if(result == null) {
			System.out.println("Invalid client ID");
		}
		else {
			System.out.println("Client name changed: " + result.getName());
		}
		
	}
	
	public void printClient() {
		warehouse.printClient();
	}
	
	//Product class
	public void addProduct() {
		String name = getToken("Enter product name: ");
		int quantity = Integer.parseInt(getToken("Enter quantity: "));
		double salePrice = Integer.parseInt(getToken("Enter sale price: "));
		double supplyPrice = Integer.parseInt(getToken("Enter supply price: "));
		Product temp = warehouse.addProduct(name, quantity, salePrice, supplyPrice);
		System.out.println("Product added: " + temp);
	}
	
	public void editProductName() {
		String name = getToken("Enter new product name: ");
		String iD = getToken("Enter ID of product: ");
		
		Product result = warehouse.editProductName(iD, name);
		if(result == null) {
			System.out.println("Invalid product ID");
		}
		else {
			System.out.println("Product name changed: " + result);
		}
	}
	
	public void editProductQuantity() {
		int quantity = Integer.parseInt(getToken("Enter new quantity: "));
		String iD = getToken("Enter ID of product: ");
		
		Product result = warehouse.editProductQuantity(iD, quantity);
		if(result == null) {
			System.out.println("Invalid product ID");
		}
		else {
			System.out.println("Product quantity changed: " + result);
		}
	}
	
	public void editProductSaleP() {
		double sale = Integer.parseInt(getToken("Enter new sale price: "));
		String iD = getToken("Enter ID of product: ");
		
		Product result = warehouse.editProductSaleP(iD, sale);
		if(result == null) {
			System.out.println("Invalid product ID");
		}
		else {
			System.out.println("Product sale price changed: " + result);
		}
	}
	
	public void editProductSupplyP() {
		double supply = Integer.parseInt(getToken("Enter new supply price: "));
		String iD = getToken("Enter ID of product: ");
		
		Product result = warehouse.editProductSuppP(iD, supply);
		if(result == null) {
			System.out.println("Invalid product ID");
		}
		else {
			System.out.println("Product supply price changed: " + result);
		}
	}
	
	public void printProduct() {
		warehouse.printProduct();
	}
	
	public void showInvoice() {

            String clientId = getToken("Enter client ID");
            if (warehouse.getClientById(clientId) == null) {
                Order o = warehouse.getOrderById(clientId);
                if (o != null) {
                    o.toString();
                }
            } else {
                System.out.println("Client is not found.");
            }

  }
	
//waitllisted orders by product
	
	public void showWaitlist() {
		String clientId = getToken("Enter client ID: ");
		
		boolean resultClient = false;
		resultClient = warehouse.checkClientId(clientId);
		if(!resultClient) {
			System.out.println("Invalid client ID");
			return;
		}
		
		Waitlist temp = warehouse.getWaitList(clientId);
		
		System.out.println("Wait list of client: " + clientId);
		System.out.println(temp);
	}
	
	//Assigning relationship between supplier and product
	public void assign() {
		String prod = getToken("Enter product ID: ");
		String supp = getToken("Enter supplier ID: ");
		
		boolean resultProd = false;
		
		resultProd = warehouse.checkProdId(prod);
		if(!resultProd) {
			System.out.println("Invalid product ID");
			return;
		}
		
		boolean resultSupp = false;

		resultSupp = warehouse.checkSuppId(supp);
		if(!resultSupp) {
			System.out.println("Invalid supplier ID");
			return;
		}
		
		warehouse.assignProdToSupp(prod, supp);
		
		
	}
	
	public void prodListQuery() {
		warehouse.prodListQuery();
	}
	
	
	public void suppListQuery() {
		warehouse.suppListQuery();
	}
	
	public void process() {
		int command;
		while((command = getCommand()) != EXIT) {
			switch(command) {
			case ADD_SUPPLIER: addSupplier();
							   break;
			case EDIT_SUPPLIER: editSupplier();
								break;
			case PRINT_SUPPLIER: printSupplier();
								 break;
			case ADD_CLIENT: addClient();
							 break;
			case EDIT_CLIENT: editClient();
							  break;
			case PRINT_CLIENT: printClient();
							   break;
			case ADD_PRODUCT: addProduct();
							  break;
			case EDIT_PRODUCT_NAME: editProductName();
									break;
			case EDIT_PRODUCT_QUANTITY: editProductQuantity();
										break;
			case EDIT_PRODUCT_SALE: editProductSaleP();
									break;
			case EDIT_PRODUCT_SUPPLY: editProductSupplyP();
									  break;
			case PRINT_PRODUCT: printProduct();
								break;
			case SHOW_WAITLIST: showWaitlist();
									break;
			//case ACCEPT_ORDERS: acceptOrders();
									  //break;
			case SHOW_INVOICE: showInvoice();
								break;
			case ASSIGN_REL:	assign();
								break;
			case PRODUCT_REL: prodListQuery();
								break;
			case SUPPLIER_REL: suppListQuery();
								break;
								 
			}
		}
	}
	
	public static void main(String[] s) {
		userInterface.instance().process();
	}
}
