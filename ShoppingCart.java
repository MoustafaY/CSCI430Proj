import java.util.*;
import java.io.*;
public class ShoppingCart implements Serializable {
  private static final long serialVersionUID = 1L;
  private List<ShoppingCartItem> cart;

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
  public String toString() {
    return cart.toString();
  }
}