package business;

import middleware.exceptions.DatabaseException;

public class Main {

	public static void main(String[] args) {
		DbClassCatalog dbclass = new DbClassCatalog();
		try {
			 System.out.println("Output " + dbclass.getAllCatalogs(1));
			//int newId = dbclass.insertNewCatalog("TestCat");
			//System.out.println("Output after adding testCat" + dbclass.getAllCatalogs(newId));
			//System.out.println("New catalog id for TestCat is " + newId); 
			//int numRowsDeleted = dbclass.deleteCatalog(4);
			//System.out.println("Num rows deleted = " + numRowsDeleted);
			
			
		}catch(DatabaseException ex){
			ex.printStackTrace();
		}

	}
	
	

}
