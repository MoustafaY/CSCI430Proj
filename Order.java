import java.io.*;
import java.util.*;
public class Order implements Serializable {
  private static final long serialVersionUID = 1L;
  private client cli;
  private String orderId;
  private ShoppingCart orderedCart;  

  public Order(client cli) {
    this.orderId = (OrderIdServer.instance()).generateId();
    this.cli = cli;
    this.orderedCart = cli.getShoppingCart();
  }

  public Iterator<ShoppingCartItem> getOrderProducts() {
    return orderedCart.getShoppingCartProducts();
  }

  public client getOrderClient() {
    return cli;
  }

  public String getOrderId() {
    return orderId;
  }

  public ShoppingCart getOrderedCart() {
    return orderedCart;
  }

  public Boolean equals(String id) {
    return this.orderId.equals(id);
  }

  public String toString() {
      return "orderId " + orderId + " clientId " + cli.getId() + " ordered_products " + orderedCart.toString();
  }
}
