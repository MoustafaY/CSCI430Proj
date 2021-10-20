import java.util.*;
import java.io.*;
public class Product implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String id;
  private int quantity;
  private double salePrice;
  private double supplyPrice;
  private List<Supplier> productSupplier = new LinkedList <Supplier>();
  private List<Waitlist> waitlistedClients = new LinkedList <Waitlist>();
  private static final String PRODUCT_STRING = "P";
  public  Product (String name, int quantity, double salePrice, double supplyPrice) {
    this.name = name;
    this.quantity = quantity;
    this.salePrice = salePrice;
	this.supplyPrice = supplyPrice;
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
  
public boolean link(Supplier supplier, int q, float sP, float suP) {
	Supplier S = new Supplier(supplier,q,sP,suP);
	return ProductSupplier.add(S) ? true: false;
}

public boolean unlink(Supplier supplier) {
	return ProductSupplier.remove(supplier) ? true: false;
}
	
public Iterator<Supplier> getSuppliers() {
	return productSupplier.iterator();
}
public Supplier searcSuppList(Supplier supplier)
{
      int i = 0;
      for (i=0; i <= productSupplier.size()-1; i++)
      {
        if ((productSupplier.get(i).getSupplier()) == supplier)
        {
          return productSupplier.get(i);
        }
      }
      return null;
}
	
  public String toString() {
        return "Product: " + name + " ID: " + id + " Qty: " + quantity + " salePrice " + salePrice + " supplyPrice " + supplyPrice;
    }
}
