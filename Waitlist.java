import java.util.*;
import java.io.*;
public class Waitlist implements Serializable {
  private static final long serialVersionUID = 1L;
  private List waitItem = new LinkedList();


  public void insertItem(Product product, int quantity) {
    waitListItem item = new waitListItem(product, quantity);
    waitItem.add(item);
  }
  

  public waitListItem getWaitItem(String id) {
	  String temp;
	  for(int i=0; i<waitItem.size(); i++) {
		  temp = ((waitListItem) waitItem.get(i)).getProductId();
		  if(temp.equals(id)) {
			  return (waitListItem) waitItem.get(i);
		  }
	  }
	  return null;
  }
  
  
  public String toString() {
    return waitItem.toString();
  }
  
  
  
  public void printWaitList() {
	  waitListItem tempItem;
	  String output;
	  for(int i=0; i<waitItem.size(); i++) {
		  tempItem = (waitListItem) waitItem.get(i);
		  output = tempItem.toString();
		  System.out.println(output);
	  }
  }
}
