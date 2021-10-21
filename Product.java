import java.util.*;
import java.io.*;
//import wareHouse.Supplier;
public class Product implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String id;
  private int quantity;
  private double salePrice;
  private double supplyPrice;
  private String supplier;
  private List<Waitlist> waitlistedClients = new LinkedList <Waitlist>();
  private static final String PRODUCT_STRING = "P";
	
  public  Product (String name, int quantity, double salePrice, double supplyPrice) {
    this.name = name;
    this.quantity = quantity;
    this.salePrice = salePrice;
	this.supplyPrice = supplyPrice;
	this.supplier = null;
    id = PRODUCT_STRING + (ProductIdServer.instance()).getId();
  }

  public String getName() {
        return name;
  }
  
  public int getQuantity() {
        return quantity;
  }
  
  public String getId() {
        return id;
  }
  
  public String getSupplier() {
	  return supplier;
  }
  
  public void setSupplier(String supp) {
	  supplier = supp;
  }
  
public double getSalePrice() {
  return salePrice;
}
public double getSupplyPrice() {
	  return supplyPrice; }
	  
  public void setName(String newName) {
        name = newName;
  }
  
  public void setQuantity(int newQuantity) {
        quantity = newQuantity;
  }
  
  public void setSalePrice(double newSalePrice) {
	    salePrice = newSalePrice;
	  }
	  public void setSupplyPrice(double newSupplyPrice) {
		supplyPrice = newSupplyPrice;
  }
  
  public boolean equals(String id) {
        return this.id.equals(id);
  }

 
	
  public String toString() {
        return "Product: " + name + " ID: " + id + " Quantity: " + quantity + " Sales Price " + salePrice + " Supplier Price " + supplyPrice;
    }
  
  public String toStringSupp() {
	return "Product: " + name + " ID: " + id + " Quantity: " + quantity + " Sales Price " + salePrice + " Supplier Price " + supplyPrice + " Supplier " + supplier; 
  }
}
