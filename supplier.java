import java.util.*;
import java.io.*;

public class supplier implements Serializable{
	private String name;
	private String id;
	private static final String SUPPLIER_STRING = "S";
	private String product;
	
	public supplier(String name) {
		this.name = name;
		id = SUPPLIER_STRING + (supplierIdServer.instance()).getId();
		this.product = null;
	}
	
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	
	public String getProduct() {
		return product;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public void setProduct(String prod) {
		product = prod;
	}
	
	public String toString() {
		String string = "Supplier name " + name + " Id " + id;
		return string;
	}
	
	public String toStringProd() {
		return "Supplier name " + name + " Id " + id + " Product " + product; 
	  }
	
}
