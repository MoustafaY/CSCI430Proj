import java.util.*;
import java.text.*;
import java.io.*;
public class userInterface{
	private static userInterface UI;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static final int EXIT = 0;
	private static final int ADD_SUPPLIER = 1;
	private static final int PRINT_SUPPLIER = 2;//number for commands here
	private static final int ADD_CLIENT = 3;
	private static final int PRINT_CLIENT = 4;
	private static final int PRINT_TRANSACTION = 5;
	private static final int ADD_PRODUCT = 6;
	private static final int PRINT_PRODUCT = 7;
	private static final int SHOW_WAITLIST = 8;
	private static final int PLACE_ORDER = 9;
	private static final int DISPLAY_CART=10;
    private static final int ADD_TO_CART=11;
    private static final int REMOVE_ITEM=12;
    private static final int CHANGE_QUANTITY = 13;
	private static final int MAKE_PAYMENT = 14;
	private static final int ASSIGN_REL = 15;
	private static final int PRODUCT_REL = 16;
	private static final int SUPPLIER_REL = 17;
	private static final int LIST_UNPAID = 18;
	private static final int ACCEPT_SHIPMENT = 19;

	

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
	
	private boolean yesOrNo(String prompt) {
	    String more = getToken(prompt + " (Y|y)[es] or anything else for no");
	    if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
	      return false;
	    }
	    return true;
	  }

	  public int getInt(String prompt) {
	    do {
	      try {
	        String item = getToken(prompt);
	        Integer num = Integer.parseInt(item);
	        return num.intValue();
	      } catch (NumberFormatException nfe) {
	        System.out.println("Please input a number ");
	      }
	    } while (true);
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
	
	
	public void printProduct() {
		warehouse.printProduct();
	}
	

	
//waitllisted orders by product
	
	public void showWaitlist() {
		String clientId = getToken("Enter client ID: ");
		
		client cli = warehouse.getClient(clientId);

		if(cli == null) {
			System.out.println("Invalid client ID");
			return;
		}
		
		Waitlist temp = warehouse.getWaitList(clientId);
		
		System.out.println("Wait list of client: " + clientId);
		warehouse.printWaitList(clientId);
	}
	
	//Assigning relationship between supplier and product
	public void assign() {
		String prodId = getToken("Enter product ID: ");
		String suppId = getToken("Enter supplier ID: ");
		

		Product prod = warehouse.getProduct(prodId);
		if(prod == null) {
			System.out.println("Invalid product ID");
			return;
		}
		
		supplier supp = warehouse.getSupplier(suppId);
		if(supp == null) {
			System.out.println("Invalid supplier ID");
			return;
		}
		
		
		warehouse.assignProdToSupp(prod, supp);
		}
		
		
		public void addToCart() {
	    client cli;
	    Product product;

	    String clientId = getToken("Enter client id to add to their shopping cart");
	    cli = warehouse.getClient(clientId);
	    if (cli != null) {
	      System.out.println("Client found:");
	      System.out.println(cli);
	      do {
	        String productId = getToken("Enter product id");
	        product = warehouse.getProduct(productId);
	        if(product != null) {
	          System.out.println("Product found:");
	          System.out.println(product);
	          int productQuantity = getInt("Enter enter quantity");
	          warehouse.addToCart(clientId, product, productQuantity);
	        } else {
	          System.out.println("Could not find that product id");
	        }
	        if (!yesOrNo("Add another product to the shopping cart?")) {
	          break;
	        }
	      } while (true);
	    } else {
	      System.out.println("Could not find that client id");
	    }
	}
	public void displayCart() {
	    client cli;

	    String clientId = getToken("Enter client id to view to their shopping cart");
	    cli = warehouse.getClient(clientId);
	    if (cli != null) {
	      System.out.println("Client found:");
	      System.out.println(cli);
	      System.out.println("Shopping Cart:");
	      warehouse.displayCart(clientId);
	    } else {
	      System.out.println("Could not find that client id");
	    }
	  }

	  public void removeItem() {
	    client cli;

	    String clientId = getToken("Enter client id to empty to their shopping cart");
	    cli = warehouse.getClient(clientId);
	    if(cli == null) {
	    	System.out.println("Invalid client Id");
	    	return;
	    }
	    String productId = getToken("Enter product Id you want to remove");
	    warehouse.removeItem(clientId, productId);
	    
	  }
	  
	  public void changeQuantity() {
			String clientId = getToken("Enter client ID: ");
			
			client cli = warehouse.getClient(clientId);
			if(cli == null) {
				System.out.println("Invalid client ID");
				return;
			}
			String productId = getToken("Enter product ID you want to change");
			int quantity = Integer.parseInt(getToken("Enter new Quantity: "));
			
			warehouse.changeQuantity(clientId, productId, quantity);
			
			
		}

	  public void placeOrder() {
	    client cli;

	    String clientId = getToken("Enter client id to place an order");
	    cli = warehouse.getClient(clientId);
	    if (cli == null) {
	    	System.out.println("Invalid client ID");
	    	return;
	    } 
	    
	    warehouse.placeOrder(clientId);
	  }
	  
	  public void processPayment() {
		    client cli;

		    String clientId = getToken("Enter client id to make a payment: ");
		    cli = warehouse.getClient(clientId);
		    if(cli == null) {
		    	System.out.println("Invalid client ID");
		    	return;
		    }
		    
		    double paymentAmount = Integer.parseInt(getToken("Enter payment amount: "));
		    if(paymentAmount < 0) {
		    	System.out.println("Invalid payment Amount: ");
		    	return;
		    }
		    
		    warehouse.makePayment(cli, paymentAmount);

		  }

		 
		 public void printTransaction() {
			 String clientId = getToken("Enter client Id to print transactions");
			 client cli = warehouse.getClient(clientId);
			 if(cli == null) {
				 System.out.println("Invalid client ID");
				 return;
			 }
			 
			 warehouse.printTransactions(clientId);
		 }
		 
		 public void listUnpaid() {
			 warehouse.listUnpaid();
		 }
	
	
	public void prodListQuery() {
		warehouse.prodListQuery();
	}
	
	
	public void suppListQuery() {
		warehouse.suppListQuery();
	}
	
	public void acceptShipment() {
		String prodId = getToken("Enter product ID in shipment:");
		Product prod = warehouse.getProduct(prodId);
		if(prod == null) {
			System.out.println("Invalid product ID");
			return;
		}
		int quantity = Integer.parseInt(getToken("Enter quantity of product"));
		warehouse.acceptShipment(prod, quantity);
	}
	
	public void process() {
		int command;
		while((command = getCommand()) != EXIT) {
			switch(command) {
			case ADD_SUPPLIER: addSupplier();
							   break;
			case PRINT_SUPPLIER: printSupplier();
								 break;
			case ADD_CLIENT: addClient();
							 break;
			case PRINT_CLIENT: printClient();
							   break;
			case PRINT_TRANSACTION: printTransaction();
									break;
			case ADD_PRODUCT: addProduct();
							  break;
			case PRINT_PRODUCT: printProduct();
								break;
			case SHOW_WAITLIST: showWaitlist();
									break;
			case PLACE_ORDER: placeOrder();
									 break;
			case DISPLAY_CART: displayCart();
								break;
			case ADD_TO_CART: addToCart();
								break;
			case REMOVE_ITEM: removeItem();
								break;
			case CHANGE_QUANTITY: changeQuantity();
									break;
			case MAKE_PAYMENT: processPayment();
								break;
			case ASSIGN_REL:	assign();
								break;
			case PRODUCT_REL: prodListQuery();
								break;
			case SUPPLIER_REL: suppListQuery();
								break;
			case LIST_UNPAID: listUnpaid();
								break;
			case ACCEPT_SHIPMENT: acceptShipment();
									break;
																	 
			}
		}
	}
	
	public static void main(String[] s) {
		userInterface.instance().process();
	}
}
