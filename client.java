import java.util.*;
import java.io.*;

public class client implements Serializable{
	private String name;
	private String id;
	private double balance;
    private ShoppingCart shoppingCart;
    private TransactionList transactionList;
    private Waitlist waitList;

	private static final String CLIENT_STRING = "M";
	
	public client(String name) {
		this.name = name;
		id = CLIENT_STRING + (clientIdServer.instance()).getId();
		this.waitList = new Waitlist();
		this.shoppingCart = new ShoppingCart();
		this.transactionList = new TransactionList();
		this.balance = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	public double getBalance() {
        return balance;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void printTransactionList() {
        transactionList.getTransactions();
    }
    
   public TransactionList getTransactionList() {
	   return transactionList;
   }
   
	public void setName(String newName) {
		name = newName;
	}
	 public void setShoppingCart(ShoppingCart cart) {
	        shoppingCart = cart;
	    }

	    public void setBalance(double newBalance) {
	        balance = newBalance;
	    }

	  public void addWaitListItem(Product prod, int quantity) {
		  waitList.insertItem(prod, quantity);
	  }
	  
	  public Waitlist getWaitList() {
		  return waitList;
	  }

	public String toString() {
		String string = "Client name " + name + " Id " + id + " Client balance: " + balance;
		return string;
	}
	
}
