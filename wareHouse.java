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
			ProductIDServer.instance();
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
		System.out.println(suppList);
	}
	
	//Client class
	public client addClient(String name) {
		client tempAdd = new client(name);
		cliList.insertClient(tempAdd);
		return tempAdd;
	}
	
	public boolean editClient(String name, String iD) {
		boolean result = cliList.editClient(name, iD);
		return result;
	}
	
	public client getClient(String iD) {
		client tempGet = cliList.getClient(iD);
		return tempGet;
	}
	
	public void printClient() {
		System.out.println(cliList);
	}
	
	//Product Class
	
	public Product addProduct(Product tempAdd) {
		prodList.insertProduct(tempAdd);
		return tempAdd;
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
	
	//public Product assignProdToSupplier
	public void printProduct() {
		System.out.println(prodList);
	}
	
	public Iterator<Product> getProductsBySupplier (Supplier s)
	{
		return s.getProducts();
	}
	
	public Iterator<Product> getProductsByClient (Client c)
	{
		return c.getClients();
	}
	
	
}
