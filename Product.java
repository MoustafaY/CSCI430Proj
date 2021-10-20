import java.util.*;
import java.io.*;
public class Product implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String id;
  private int quantity;
  private double salePrice;
  private double supplyPrice;
  
  private static final String PRODUCT_STRING = "P";
  public  Product (String name, int quantity, double salePrice, double supplyPrice) {
    this.name = name;
    this.quantity = quantity;
    this.salePrice = salePrice;
	this.supplyPrice = supplyPrice;
    id = PRODUCT_STRING + (ProductIDServer.instance()).getId();
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
        return "Product: " + name + " ID: " + id + " Qty: " + quantity + " salePrice " + salePrice + " supplyPrice " + supplyPrice;
    }
}
