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
	
	public boolean checkProdId(String prod) {
		if(getProductById(prod) == null) {
			return false;
		}
		return true;
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
	
	//Product Class
	
	public Product addProduct(String name, int quantity, double salePrice, double supplyPrice) {
		Product temp = prodList.insertProduct(name, quantity, salePrice, supplyPrice);
		return temp;
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
		
		
		public void assignProdToSupp(String prod, String supp) {
			prodList.assignSupp(prod, supp);
			Product temp = prodList.getProduct(prod);
			suppList.assignProd(supp, prod, temp.getSupplyPrice());
		}
		

	//display a client's shopping cart
	public Boolean displayCart(String clientId) {
	    client cli = getClientById(clientId);
	    if ( cli == null ) {
	        return false;
	    }
	    Iterator<ShoppingCartItem> cartIterator = cli.getShoppingCart().getShoppingCartProducts();
	    while (cartIterator.hasNext()){
	        System.out.println(cartIterator.next());
	     }
	    return true;
	}
	// add product to a client's shopping cart
	    public Boolean addToCart(String clientId, Product product, int quantity) {
	        client cli = getClientById(clientId);
	        if ( cli == null ) {
	            return false;
	        }
	        cli.getShoppingCart().insertProductToCart(product, quantity);
	        return true;
	    }

	    // empty a client's shopping cart
	    public Boolean emptyCart(String clientId) {
	         client cli = getClientById(clientId);
	        if ( cli == null ) {
	            return false;
	        }
	        cli.setShoppingCart(new ShoppingCart());;
	        return true;
	    }
	//place order and empty client's shopping cart
	public Boolean placeOrder(String clientId) {
	    client cli = getClientById(clientId);
	    if ( cli == null ) {
	        return false;
	    }

	    Iterator<ShoppingCartItem> cartIterator = cli.getShoppingCart().getShoppingCartProducts();
	    while(cartIterator.hasNext()) {
	        ShoppingCartItem cartItem = cartIterator.next();
	        String productId = cartItem.getProduct().getId();
	        InventoryItem inventoryItem = getInventoryItemById(productId);
	        
	        if(inventoryItem != null) {
	            int quantityInStock = inventoryItem.getQuantity();
	            int cartQuantity = cartItem.getQuantity();
	            int newQuantityInStock = 0;
	            newQuantityInStock = quantityInStock - cartQuantity;
	            if(newQuantityInStock < 0) {
	                int waitItemQuantity = newQuantityInStock * -1;
	                Product temp = prodList.getProduct(productId);
	                cli.addWaitListItem(temp, waitItemQuantity);
	                inventoryItem.setQuantity(0);
	            } else {
	                inventoryItem.setQuantity(newQuantityInStock);
	            }
	        }
	    }
	    
	    return true;
	}

	 //get client by id
	    public client getClientById(String id) {
	        Iterator<client> clients = clientList.instance().getClients();

	        client p = null;
	        while (clients.hasNext() && p == null) {
	            client tmp = clients.next();
	            if ( tmp.equals(id) ) {
	                p = tmp;
	            }
	        }

	        return p;
	    }
	 //get product by id
	    public Product getProductById(String id) {
	        Iterator<Product> products = ProductList.instance().getProducts();

	        Product p = null;
	        while (products.hasNext() && p == null) {
	            Product tmp = products.next();
	            if ( tmp.equals(id)) {
	                p = tmp;
	            }
	        }

	        return p;
	    }
	    
	    //get supplier by id
	    public supplier getSupplierById(String id) {
	        Iterator<supplier> suppliers = supplierList.instance().getSuppliers();

	        supplier s = null;
	        while (suppliers.hasNext() && s == null) {
	            supplier tmp = suppliers.next();
	            if ( tmp.equals(id)) {
	                s = tmp;
	            }
	        }

	        return s;
	    }
	    
	    //get Order by id
	    public Order getOrderById(String id) {
	        Iterator<Order> orders = OrderList.instance().getOrders();

	        Order o = null;
	        while (orders.hasNext() && o == null) {
	            Order tmp = orders.next();
	            if ( tmp.equals(id)) {
	                o = tmp;
	            }
	        }

	        return o;
	    }

	//get item in inventory by id
	    public InventoryItem getInventoryItemById(String id) {
	        Iterator<InventoryItem> inventory = Inventory.instance().getInventory();

	        InventoryItem item = null;
	        while (inventory.hasNext() && item == null) {
	            InventoryItem tmp = inventory.next();
	            if ( tmp.equals(id)) {
	                item = tmp;
	            }
	        }

	        return item;
	    }
	//get invoice by id
	public Invoice getInvoiceById(String id) {
	Iterator<Invoice> invoices = InvoiceList.instance().getInvoices();

	Invoice i = null;
	while (invoices.hasNext() && i == null) {
	    Invoice tmp = invoices.next();
	    if ( tmp.equals(id)) {
	        i = tmp;
	    }
	}

	return i;
	}
	// add product to inventory
	public Boolean addToInventory(String productId, int quantity) {
	Product product = this.getProductById(productId);
	if ( product == null ) {
	    return false;
	}
	InventoryItem item = getInventoryItemById(productId);
	if(item == null) {
	    Inventory.instance().addToInventory(product, quantity);
	} else {
	    int currentQuantity = item.getQuantity();
	    int newQuantity = currentQuantity += quantity;
	    item.setQuantity(newQuantity);
	}
	return true;
	}


	//make payment
	public Boolean makePayment(String clientId, double amount) {
	    client cli = this.getClientById(clientId);
	    if ( cli == null ) {
	        return false;
	    }
	    cli.addBalance(amount);
	    Transaction transaction = new Transaction("Payment Made", amount);
	    cli.getTransactionList().insertTransaction(transaction);
	    return true;
	}

	// get a client's transactions
	public Iterator<Transaction> getTransactions(String clientId) {
	    client cli = this.getClientById(clientId);
	    if ( cli == null ) {
	        return null;
	    }
	    return cli.getTransactionList().getTransactions();
	}

	public Iterator<InventoryItem> getInventory() {
	    return Inventory.instance().getInventory();
	}

	public Boolean addProductToInventory(String id, int quantity) {
	    Product p = prodList.getProduct(id);
	    if(p == null) {
	        return false;
	    }
	    Inventory.instance().addToInventory(p, quantity);
	    return true;
	}
	
	//print waitlist
		public Waitlist getWaitList(String clientId) {
			client temp = cliList.getClient(clientId);
			return temp.getWaitList();
		}
		
		
	//Query assignments
		public void prodListQuery() {
			prodList.queryAssign();
		}
		
		public void suppListQuery() {
			suppList.queryAssign();
		}

	}
	
	
	
