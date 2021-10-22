import java.util.*;
import java.io.*;
public class ProductList implements Serializable {
  private static final long serialVersionUID = 1L;
  private List products = new LinkedList();
  private static ProductList productList;
  public ProductList() {
  }
  public static ProductList instance() {
    if (productList == null) {
      return (productList = new ProductList());
    } else {
      return productList;
    }
  }

  public Product insertProduct(String name, int quantity, double salePrice, double supplyPrice) {
	Product temp = new Product(name, quantity, salePrice, supplyPrice);
    products.add(temp);
    return temp;
  }
  
  public void assignSupp(Product prod, supplier supp) {
	  String temp;
		for(int i=0; i<products.size(); i++) {
			temp = ((Product) products.get(i)).getId();
			if(temp.equals(prod.getId())) {
				((Product) products.get(i)).setSupplier(supp);
			}
		}
  }

  public Product getProduct(String iD){
	  String temp;
	  for(int i=0; i<products.size(); i++) {
		  temp = ((Product) products.get(i)).getId();
		  if(temp.equals(iD)) {
			  return (Product) products.get(i);
		  }
	  }
     return null;
  }
  
  public void acceptShipment(Product prod, int quantity) {
	  Product temp;
	  for(int i=0; i<products.size(); i++) {
		  temp = (Product) products.get(i);
		  if(temp.getId().equals(prod.getId())) {
			  int newQuantity = temp.getQuantity() + quantity;
			  temp.setQuantity(newQuantity);
			  return;
		  }
	  }
  }
  
  public boolean editProductName(String iD, String name) {
		String temp;
		for(int i=0; i<products.size(); i++) {
			temp = ((Product) products.get(i)).getId();
			if(temp.equals(iD)) {
				((Product) products.get(i)).setName(name);
				return true;
			}
		}
		return false;
	}
  
  public boolean editProductQuantity(String iD, int quantity) {
		String temp;
		for(int i=0; i<products.size(); i++) {
			temp = ((Product) products.get(i)).getId();
			if(temp.equals(iD)) {
				((Product) products.get(i)).setQuantity(quantity);
				return true;
			}
		}
		return false;
	}
  
  public boolean editProductSale(String iD, double sale) {
		String temp;
		for(int i=0; i<products.size(); i++) {
			temp = ((Product) products.get(i)).getId();
			if(temp.equals(iD)) {
				((Product) products.get(i)).setSalePrice(sale);
				return true;
			}
		}
		return false;
	}
  
  public boolean editProductSupply(String iD, double supply) {
		String temp;
		for(int i=0; i<products.size(); i++) {
			temp = ((Product) products.get(i)).getId();
			if(temp.equals(iD)) {
				((Product) products.get(i)).setSupplyPrice(supply);
				return true;
			}
		}
		return false;
	}
  
  public Iterator getProducts() {
		return products.iterator();
	}
  
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(productList);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (productList != null) {
        return;
      } else {
        input.defaultReadObject();
        if (productList == null) {
          productList = (ProductList) input.readObject();
        } else {
          input.readObject();
        }
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
  }
  public String toString() {
    return products.toString();
  }
  
  public void printProd() {
		String temp;
		Product prod;
		supplier supp;
		  for(int i=0; i<products.size(); i++) {
				prod = (Product) products.get(i);
				supp = prod.getSupplier();
				if(supp == null)
				{
					System.out.println(((Product) products.get(i)).toString());
				}
				else
				{
					System.out.println(((Product) products.get(i)).toStringSupp());
				}
	  }
	}
  
  //Query assignments
  public void queryAssign() {
	  supplier temp;
	  for(int i=0; i<products.size(); i++) {
			temp = ((Product) products.get(i)).getSupplier();
			if(temp != null) {
				System.out.println(((Product) products.get(i)).toStringSupp());
			}
		}
  }
}
