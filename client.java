import java.util.*;
import java.io.*;

public class client implements Serializable{
	private String name;
	private String id;
	private double balance;
        private ShoppingCart shoppingCart;
        private TransactionList transactionList;

	private static final String CLIENT_STRING = "M";
	
	public client(String name) {
		this.name = name;
		id = CLIENT_STRING + (clientIdServer.instance()).getId();
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

	    public void addBalance(double bal) {
	        balance += bal;
	    }

	    public void subtractBalance(double bal) {
	        balance -= bal;
	    }

	public String toString() {
		String string = "Client name " + name + " Id " + id + " Client balance: " + balance;
		return string;
	}
	
}
