import java.util.*;
import java.io.*;
public class wareHouse implements Serializable{
	private supplierList suppList;
	private clientList cliList;
	private ProductList prodList;
	private static wareHouse warehouse;
	
	wareHouse() {
		suppList = supplierList.instance();//class list here
		cliList = clientList.instance();
		prodList = ProductList.instance();
	}
	
	public static wareHouse instance() {
		if(warehouse == null) {
			supplierIdServer.instance();//class id server here
			clientIdServer.instance();
			ProductIdServer.instance();
			return (warehouse = new wareHouse());
		}else {
			return warehouse;
		}
	}
	
	//Functions here
	
	//Supplier class
	public supplier addSupplier(String name) {
		supplier temp = suppList.insertSupplier(name);
		return temp;
	}
	
	public supplier getSupplier(String iD) {
		return  suppList.getSupplier(iD);
	}
	
	public supplier editSupplier(String name, String iD) {
		boolean flag;
		supplier tempEdit;
		
		flag = suppList.editSupplier(name, iD);
		if(flag == true) {
			tempEdit = suppList.getSupplier(iD);
			return tempEdit;
		}
		else {
			return null;
		}
	}
	
	public void printSupplier() {
		suppList.printSupp();
	}
	
	
	//Client class
	public client addClient(String name) {
		client temp = cliList.insertClient(name);
		return temp;
	}
	
	public client editClient(String name, String iD) {
		client result = cliList.editClient(name, iD);
		if(result == null) {
			return null;
		}
		else {
			return cliList.getClient(iD);
		}
	}
	
	public client getClient(String iD) {
		return  cliList.getClient(iD);
	}
	
	public void printClient() {
		System.out.println(cliList);
	}
	
	public boolean checkClientId(String client) {
		if(cliList.getClient(client) == null) {
			return false;
		}
		return true;
	}
	
	public void listUnpaid() {
		cliList.listUnpaid();
	}
	
	//Product Class
	
	public Product addProduct(String name, int quantity, double salePrice, double supplyPrice) {
		Product temp = prodList.insertProduct(name, quantity, salePrice, supplyPrice);
		return temp;
	}
	
	public Product getProduct(String iD) {
		return  prodList.getProduct(iD);
	}
	
	public Product editProductName(String iD, String name) {
		boolean result = prodList.editProductName(iD, name);
		if(result == true) {
			Product tempEdit = prodList.getProduct(iD);
			return tempEdit;
		}
		else {
			return null;
		}
	}
	
	public Product editProductQuantity(String iD, int quantity) {
		boolean result = prodList.editProductQuantity(iD, quantity);
		if(result == true) {
			Product tempEdit1 = prodList.getProduct(iD);
			return tempEdit1;
		}
		else {
			return null;
		}
	}
	
	public Product editProductSaleP(String iD, double saleP) {
		boolean result = prodList.editProductSale(iD, saleP);
		if(result == true) {
			Product tempEdit1 = prodList.getProduct(iD);
			return tempEdit1;
		}
		else {
			return null;
		}
	}
	
	public Product editProductSuppP(String iD, double suppP) {
		boolean result = prodList.editProductSupply(iD, suppP);
		if(result == true) {
			Product tempEdit1 = prodList.getProduct(iD);
			return tempEdit1;
		}
		else {
			return null;
		}
	}
	
	public void printProduct() {
		prodList.printProd();
	}
	
	public boolean checkSuppId(String supp) {
		if(suppList.getSupplier(supp) == null) {
			return false;
		}
		return true;
	}
	
	//public Product assignProdToSupplier
		
		
		public void assignProdToSupp(Product prod, supplier supp) {
			prodList.assignSupp(prod, supp);
			suppList.assignProd(supp, prod, prod.getSupplyPrice());
		}
		

	//display a client's shopping cart
	public void displayCart(String clientId) {
	    client cli = getClient(clientId);
	    if ( cli == null ) {
	        return;
	    }
	    ShoppingCart cart = cli.getShoppingCart();
	    cart.printCart();
	}
	// add product to a client's shopping cart
	    public Boolean addToCart(String clientId, Product product, int quantity) {
	        client cli = getClient(clientId);
	        if ( cli == null ) {
	            return false;
	        }
	        cli.getShoppingCart().insertProductToCart(product, quantity);
	        return true;
	    }

	    // empty a client's shopping cart
	    public void emptyCart(String clientId) {
	        client cli = getClient(clientId);
	        cli.setShoppingCart(new ShoppingCart());;
	    }
	    
	//remove item from shopping cart
	public void removeItem(String clientId, String productId) {
		client cli = getClient(clientId);
		cli.getShoppingCart().removeItem(productId);
	}
	
	//Change quantity of item in shopping cart
	public void changeQuantity(String clientId, String productId, int quantity) {
		client cli = getClient(clientId);
		cli.getShoppingCart().changeQuantity(productId, quantity);
	}
	
	//place order and empty client's shopping cart
	public void placeOrder(String clientId) {
	    client cli = getClient(clientId);
	    if ( cli == null ) {
	        return;
	    }

	    ShoppingCart shopCart = cli.getShoppingCart();
	    ShoppingCartItem itemCart;
	    double balanceAmount = cli.getBalance();
	    for(int i=0; i<shopCart.cart.size(); i++) {
			  itemCart = (ShoppingCartItem) shopCart.cart.get(i);
			  String productId = itemCart.getProductId();
			  Product temp = prodList.getProduct(productId);
			  int quantityInStock = temp.getQuantity();
			  int cartQuantity = itemCart.getQuantity();
			  
			  int newQuantity = quantityInStock - cartQuantity;
			  if(newQuantity < 0) {
				  int waitItemQuantity = newQuantity * -1;
				  balanceAmount += quantityInStock * temp.getSalePrice();
				  cli.setBalance(balanceAmount);
				  cli.addWaitListItem(temp, waitItemQuantity);
				  temp.setQuantity(0);
			  }else {
				  balanceAmount += cartQuantity * temp.getSalePrice();
				  cli.setBalance(balanceAmount);
				  temp.setQuantity(newQuantity);
			  }
		  }
	    emptyCart(clientId);
	   
	}
	    

	//make payment
	public void makePayment(client cli, double amount) {
		double oldAmount = cli.getBalance();
		double newAmount = oldAmount - amount;
		if(newAmount< 0) {
			System.out.println("Invalid payment amount");
			return;
		}
	    cli.setBalance(newAmount);
	    cli.getTransactionList().insertTransaction(cli, amount);
	}

	// get a client's transactions
	
	public void printTransactions(String clientId) {
	    client cli = this.getClient(clientId);
	    cli.printTransactionList();
	}


	
	//print waitlist
		public Waitlist getWaitList(String clientId) {
			client temp = cliList.getClient(clientId);
			return temp.getWaitList();
		}
		
		public void printWaitList(String clientId) {
			Waitlist temp = getWaitList(clientId);
			temp.printWaitList();
		}
		
	//accept shipments
		public void acceptShipment(Product prod, int quantity) {
			quantity = cliList.acceptShipment(prod, quantity);
			if(quantity > 0) {
				prodList.acceptShipment(prod, quantity);
			}
		}
		
		
	//Query assignments
		public void prodListQuery() {
			prodList.queryAssign();
		}
		
		public void suppListQuery() {
			suppList.queryAssign();
		}

	}
	
	
	
