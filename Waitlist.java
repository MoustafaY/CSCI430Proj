import java.util.*;
import java.io.*;
public class Waitlist implements Serializable {
  private static final long serialVersionUID = 1L;
  private List<waitListItem> waitItem;

  public Waitlist() {
    waitItem = new LinkedList<waitListItem>();
  }


  public void insertItem(Product product, int quantity) {
    waitListItem item = new waitListItem(product, quantity);
    waitItem.add(item);
  }
  
  
  public Iterator<waitListItem> getWaitListProducts() {
    return waitItem.iterator();
  }

  
  public String toString() {
    return waitItem.toString();
  }
}
