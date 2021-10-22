import java.util.*;
import java.io.*;
import java.lang.*;

public class waitListItem implements Serializable {
  private static final long serialVersionUID = 1L;
  private Product product;
  private int quantity;
  

  public waitListItem(Product p, int q){
    this.product  = p;
    this.quantity = q;
  }
  
  public Product getProduct(){
    return product;
  }
  
  public String getProductId() {
	  return product.getId();
  }

  public int getQuantity(){
    return quantity;
  }

  public void updateQuantity(int newQ){
    this.quantity = newQ;
  }
  
  public String toString() {
    return "Product Name: " + product.getName() + ", Quantity: " + quantity;
  }
}