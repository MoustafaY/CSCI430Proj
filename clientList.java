import java.util.*;
import java.io.*;
public class clientList implements Serializable{
	private List clients = new LinkedList();
	private static clientList cliList;
	clientList() {
		
	}
	
	public static clientList instance() {
		if(cliList == null) {
			return(cliList = new clientList());
		}else {
			return cliList;
		}
	}
	
	public client insertClient(String name) {
		client temp = new client(name);
		clients.add(temp);
		return temp;
	}
	
	public client editClient(String na, String iD) {
		String temp;
		for(int i=0; i<clients.size(); i++) {
			temp = ((client) clients.get(i)).getId();
			if(temp.equals(iD)) {
				((client) clients.get(i)).setName(na);
				return (client) clients.get(i);
			}
		}
		return null;
	}
	
	
	public Iterator getClients() {
		return clients.iterator();
	}
	
	public client getClient(String iD){
		  String temp;
		  for(int i=0; i<clients.size(); i++) {
			  temp = ((client) clients.get(i)).getId();
			  if(temp.equals(iD)) {
				  return (client) clients.get(i);
			  }
		  }
	     return null;
	  }
	
	public void printClients() {
		client temp;
		for(int i=0; i<clients.size(); i++) {
			  temp = (client) clients.get(i);
			  System.out.println(temp.toString());
		  }
	}
	
	public void listUnpaid() {
		client temp;
		for(int i=0; i<clients.size(); i++) {
			  temp = (client) clients.get(i);
			  if(temp.getBalance() > 0) {
				  System.out.println(temp.toString());
			  }
		  }
	}
	
	public int acceptShipment(Product prod, int Quantity) {
		Waitlist listWait;
		waitListItem itemWait;
		double amtDue;
		for(int i=0; i<clients.size(); i++) {
			  listWait = ((client) clients.get(i)).getWaitList();
			  itemWait = listWait.getWaitItem(prod.getId());
			  if(itemWait != null) {
				  amtDue = ((client) clients.get(i)).getBalance();
				  int oldQuantity = itemWait.getQuantity();
				  if(Quantity == 0) {
					  return 0;
				  }
				  if(Quantity > oldQuantity) {
					  Quantity -= oldQuantity;
					  amtDue += oldQuantity * itemWait.getProduct().getSalePrice();
					  ((client) clients.get(i)).setBalance(amtDue);
					  listWait.remItem(prod.getId());
				  }
				  else {
					  oldQuantity -= Quantity;
					  amtDue += Quantity * itemWait.getProduct().getSalePrice();
					  ((client) clients.get(i)).setBalance(amtDue);
					  itemWait.updateQuantity(oldQuantity);
					  return 0;
				  }
			  }
		  }
		return Quantity;
	}
	
	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(cliList);
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private void readObject(java.io.ObjectInputStream input) {
		try {
			if(cliList != null) {
				return;
			}else {
				input.defaultReadObject();
				if(cliList == null) {
					cliList = (clientList) input.readObject();
				}else {
					input.readObject();
				}
			}
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	
	public String toString() {
		return clients.toString();
	}
}