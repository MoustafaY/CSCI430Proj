import java.io.*;
import java.text.*;
import java.util.*;

public class Transaction implements Serializable {
  private static final long serialVersionUID = 1L;
  private double amount;
  private client cli;

  public Transaction(client clI, double amt) {
	this.cli = clI;
    this.amount = amt;
  }


  public String toString() {
      return "Client: " + cli + ", Transaction Amount: $" + amount;
  }
}
