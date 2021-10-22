import java.util.*;
import java.io.*;

public class TransactionList implements Serializable {
  private static final long serialVersionUID = 1L;
  private List<Transaction> transactionList;

  public TransactionList() {
    transactionList = new LinkedList<Transaction>();
  }

  public boolean insertTransaction(client cli, double amt) {
	Transaction transaction = new Transaction(cli, amt);
    transactionList.add(transaction);
    return true;
  }
  
  public void getTransactions() {
	  for(int i=0; i<transactionList.size(); i++) {
			System.out.println(((Transaction) transactionList.get(i)).toString());
		}
  }
  
  public String toString() {
    return transactionList.toString();
  }
}
