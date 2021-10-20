import java.util.*;
import java.io.*;

public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    private String productID;
    private String productname;
    private float productPrice;
    private float supplyPrice;
    private static final String PRODUCT_STRING = "P";
    private int quantity;
    private List<Waitlist> waitlistedClients = new LinkedList<Waitlist>();
    private List<Supplier> sellingSuppliers = new LinkedList<Supplier>();


    public Product (String productname, int quantity, float productPrice, float supplyPrice)
    {
        this.productname = productname;
        this.productPrice = productPrice;
        this.productID = PRODUCT_STRING + (ProductIdServer.instance()).getId();
        this.quantity = quantity;
        this.supplyPrice = supplyPrice;
    }

    public void setProductName(String newName)
	{
		this.productname = newName;
	}

    public void setproductPrice(float newproductprice)
    {
        this.productPrice = newproductprice;
    }

    public String getproductname()
    {
        return productname;
    }
    public String getproductID()
    {
        return productID;
    }
    public float getproductPrice()
    {
        return productPrice;
    }
    public float getsupplyPrice()
    {
        return supplyPrice;
    }
    public int getQuantity()
    {
        return quantity;
    }
    public Iterator<Waitlist> getWaitlistedClients()
    {
      return waitlistedClients.iterator();
    }
    public boolean addToWaitlist(Waitlist w)
    {
        return waitlistedClients.add(w);
    }
    public boolean addToSellingSuppliers(Supplier s) {
		return sellingSuppliers.add(s);
	}


    public void setproductQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String toString()
    {
        String string = "Product Name: " + productname + ", Product ID: " + productID + ", Product Quantity: " + quantity + ", Product Price: " + productPrice + ", Supply Price: " + supplyPrice;
        return string; 
    }

    public void updateQuantity(int q) {
        setproductQuantity(q);
    }
}
