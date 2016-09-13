package business;

import java.util.List;

import middleware.exceptions.DatabaseException;

public class Main {

	public static void main(String[] args) {
		try {
			
			doInsert();
			doRead();
			//doUpdate();
			//doDelete();
			
		} catch(DatabaseException e) {
			e.printStackTrace();
		}
		

	}
	private static void doRead() throws DatabaseException {
		DbClassAddress dbclass = new DbClassAddress();
		List<Address> addresses = dbclass.getCustAddresses("1");
		addresses.forEach(addr -> System.out.println(addr));
	}
	private static void doInsert() throws DatabaseException {
		DbClassAddress dbclass = new DbClassAddress();
		int result = dbclass.insertNewAddress("1", "1001 Last Day St.", "Georgetown", "IA", "77681");
		System.out.println("Address id for new address: " + result);
		
	}
	
	private static void doUpdate() throws DatabaseException {
		DbClassAddress dbclass = new DbClassAddress();
		int result = dbclass.updateFairfieldAddresses("11221");
		System.out.println("Num rows affected: " + result);
		
	}
	
	
	private static void doDelete() throws DatabaseException {
		DbClassAddress dbclass = new DbClassAddress();
		int result = dbclass.deleteFairfieldAddresses("11221");
		System.out.println("Num rows affected: " + result);
		
	}
	

}
