import java.util.*;
import java.lang.*;
import java.io.*;
public class Item implements Serializable  {
  private static final long serialVersionUID = 1L;
  private String clientId;
  private String productId;
  private Product product;
  private int quantity;
  private double total;

  public Item(Product product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }
  
  public Item(Product product, int quantity, double total) {
    this.product = product;
    this.quantity = quantity;
    this.total = total;
  }
  
 public Item(String pId, int quantity, double total) {
        this.productId = pId;
        this.quantity = quantity;
        this.total = total;
  }
  public void associateClientID(String clientId) {
        this.clientId = clientId;
    }
  
  public Product getProduct() {
    return product;
  }
  public String getProductId() {
    return product.getproductID();
  }
  public String getProductName() {
    return product.getproductname();
  }
  public int getQuantity() {
    return quantity;
  }
  public double getTotal() {
        return total;
  }
  public float getPrice() {
    return product.getproductPrice()* quantity;
  }
  public float getProductPrice() {
    return product.getproductPrice()* quantity;
  }
  public void setQuantity(int newQuantity) {
    quantity = newQuantity;
  }
  public boolean equals(String id) {
    return id.equals(product.getproductID());
  }
  
  public String toString() {
    return "Product Id: " + product.getproductID()+ ", Quantity: " + quantity;
  }
  public String print() {
        return "Product ID: " + product.getproductID() + " Quantity: " + quantity + " Total: " + total;
   }
}
