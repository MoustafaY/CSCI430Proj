import java.util.*;
import java.io.*;
public class Product implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String id;
  private int quantity;
  private double salePrice;
  private double supplyPrice;
  private List<supplier> productSupplier = new LinkedList <supplier>();
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
  
public boolean link(supplier supp) {
	supplier S = new supplier(supp);
	return productSupplier.add(S) ? true: false;
}

public boolean unlink(supplier supp) {
	return productSupplier.remove(supp) ? true: false;
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

public Iterator<Waitlist> getWaitlistedClients() {
	return waitlistedClients.iterator();
}
	
public List<Supplier> getList() {
	return productSupplier;
}
	
public boolean addClientToWaitlist(Waitlist w) {
	return waitlistedClients.add(w);
}
	
  public String toString() {
        return "Product: " + name + " ID: " + id + " Qty: " + quantity + " salePrice " + salePrice + " supplyPrice " + supplyPrice;
    }
}
