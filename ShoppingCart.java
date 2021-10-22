import java.util.*;
import java.io.*;
import java.util.LinkedList;
public class ShoppingCart implements Serializable {
  private static final long serialVersionUID = 1L;
  List<ShoppingCartItem> cart;

  public ShoppingCart() {
    cart = new LinkedList<ShoppingCartItem>();
  }

  // insert a product into the shopping cart and the quantity
  public boolean insertProductToCart(Product product, int quantity) {
    ShoppingCartItem item = new ShoppingCartItem(product, quantity);
    cart.add(item);
    return true;
  }
  
  public Iterator<ShoppingCartItem> getShoppingCartProducts() {
    return cart.iterator();
  }
  
  public void removeItem(String productId) {
	  String temp;
	  for(int i=0; i<cart.size(); i++) {
		  temp = ((ShoppingCartItem) cart.get(i)).getProductId();
		  if(temp.equals(productId)) {
			  cart.remove(i);
			  return;
		  }
	  }
     System.out.println("Invalid product ID");
  }

  public double getTotalPrice() {
    double totalPrice = 0;
    Iterator<ShoppingCartItem> cartIterator = cart.iterator();

    while (cartIterator.hasNext()){
      ShoppingCartItem item = cartIterator.next();
      totalPrice += (item.getProduct().getSalePrice() * item.getQuantity());
    }
    
    return totalPrice;
  }
  
  public String toString() {
    return cart.toString();
  }
  
  public void changeQuantity(String productId, int quantity) {
	  String temp;
	  for(int i=0; i<cart.size(); i++) {
		  temp = ((ShoppingCartItem) cart.get(i)).getProductId();
		  if(temp.equals(productId)) {
			  ((ShoppingCartItem) cart.get(i)).setQuantity(quantity);
			  return;
		  }
	  }
     System.out.println("Invalid product ID");
  }
  
  public void printCart() {
	  for(int i=0; i<cart.size(); i++) {
		  System.out.println((ShoppingCartItem) cart.get(i));
	  }
  }
}
