import java.util.*;
import java.io.*;

public class supplier implements Serializable{
	private String name;
	private String id;
	private static final String SUPPLIER_STRING = "S";
	private Product product;
	private double supplierPrice;
	
	public supplier(String name) {
		this.name = name;
		id = SUPPLIER_STRING + (supplierIdServer.instance()).getId();
		this.product = null;
		this.supplierPrice = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public double getPrice() {
		return supplierPrice;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public void setProduct(Product prod, double price) {
		product = prod;
		supplierPrice = price;
	}
	
	public String toString() {
		String string = "Supplier name " + name + " Id " + id;
		return string;
	}
	
	public String toStringProd() {
		return "Supplier name " + name + " Id " + id + " Product " + product.getName() + " Supplier Price " + supplierPrice; 
	  }
	
}