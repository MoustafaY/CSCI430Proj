import java.util.*;
import java.io.*;
public class Waitlist implements Serializable {
  private static final long serialVersionUID = 1L;
  private List<waitListItem> waitItem;
  private String clientID;

  public Waitlist(String cliID) {
    waitItem = new LinkedList<waitListItem>();
    this.clientID = cliID;
  }


  public boolean insertProductToCart(String clientId, Product product, int quantity) {
    waitListItem item = new waitListItem(product, quantity);
    waitItem.add(item);
    return true;
  }
  
  public String getClientID() {
	  return clientID;
  }
  
  public Iterator<waitListItem> getWaitListProducts() {
    return waitItem.iterator();
  }

  
  public String toString() {
    return waitItem.toString();
  }
}
