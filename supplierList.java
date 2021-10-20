import java.util.*;
import java.io.*;
public class supplierList implements Serializable{
	private List suppliers = new LinkedList();
	private static supplierList suppList;
	supplierList() {
		
	}
	
	public static supplierList instance() {
		if(suppList == null) {
			return(suppList = new supplierList());
		}else {
			return suppList;
		}
	}
	
	public supplier insertSupplier(String name) {
		supplier temp = new supplier(name);
		suppliers.add(temp);
		return temp;
	}
	
	public boolean editSupplier(String na, String iD) {
		String temp;
		for(int i=0; i<suppliers.size(); i++) {
			temp = ((supplier) suppliers.get(i)).getId();
			if(temp.equals(iD)) {
				((supplier) suppliers.get(i)).setName(na);
				return true;
			}
		}
		return false;
	}
	
	public supplier getSupplier(String iD) {
		String temp;
		supplier dummy = new supplier("Dummy");
		for(int i=0; i<suppliers.size(); i++) {
			temp = ((supplier) suppliers.get(i)).getId();
			if(temp.equals(iD)) {
				return (supplier) suppliers.get(i);
			}
		}
		return dummy;
	}
	
	public Iterator getSuppliers() {
		return suppliers.iterator();
	}
	
	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(suppList);
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private void readObject(java.io.ObjectInputStream input) {
		try {
			if(suppList != null) {
				return;
			}else {
				input.defaultReadObject();
				if(suppList == null) {
					suppList = (supplierList) input.readObject();
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
		return suppliers.toString();
	}
}
