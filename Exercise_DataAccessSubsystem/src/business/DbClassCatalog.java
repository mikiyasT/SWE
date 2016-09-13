package business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import middleware.dataaccess.DataAccessSubsystemFacade;
import middleware.exceptions.DatabaseException;
import middleware.externalinterfaces.DataAccessSubsystem;
import middleware.externalinterfaces.DbClass;
import java.util.*;

public class DbClassCatalog implements DbClass {
	enum Type {READ_ALL, INSERT, DELETE};
	Type qtype;
	String insert_query = "INSERT INTO catalogtype " + "(catalogname)" + "VALUES(?)";
	Object[] insert_params = null;
	int[] insert_paramTypes = null;
	
	String read_query =  "SELECT catalogid,catalogname FROM catalogtype WHERE catalogid = ?";
	//String read_query =  "SELECT catalogid,catalogname FROM catalogtype";
	Object[] read_params = null;
	int[] read_paramTypes = null;
	
	String delete_query =  "DELETE FROM catalogtype WHERE catalogid=?";
	Object[] delete_params = null;
	int[] delete_paramTypes = null;
	
	List<Catalog> cataloglist = new ArrayList<Catalog>();
	
	private static final String DB_URL_PROD = "jdbc:mysql:///ProductsDb";
	private static final String DB_URL_ACCT = "jdbc:mysql:///AccountsDb";
	
	
	public List<Catalog> getAllCatalogs(int id) throws DatabaseException{
		//implement
		qtype = Type.READ_ALL;
		read_params = new Object[]{id};//null;
		read_paramTypes = new int[]{Types.SMALLINT};//null;
		
		//read_params = null;
		//read_paramTypes = null;
		
		DataAccessSubsystemFacade dss = new DataAccessSubsystemFacade();
		dss.establishConnection(this);
		dss.read();
		dss.releaseConnection();
		
		return cataloglist;
	}
	
	public int insertNewCatalog(String catalogName)  throws DatabaseException{
		//implement
		qtype = Type.INSERT;
		insert_params = new Object[]{"testCatalog"};
		insert_paramTypes = new int[]{Types.VARCHAR};
		DataAccessSubsystemFacade dss = new DataAccessSubsystemFacade();
		int res = dss.insertWithinTransaction(this);
		return res;  
	}
	
	public int deleteCatalog(int id) throws DatabaseException{
		qtype = Type.DELETE;
		delete_params = new Object[]{id};
		delete_paramTypes = new int[]{Types.SMALLINT};
		DataAccessSubsystemFacade dss = new DataAccessSubsystemFacade();
		int ret = dss.deleteWithinTransaction(this);
		return ret;  //num rows updated
	}
	
	///interface implementations
	@Override
	public void populateEntity(ResultSet resultSet) throws DatabaseException {
		cataloglist = new ArrayList<Catalog>();
		int id = 0;
		String name = null;
		try {
			while(resultSet.next()){
				id = Integer.parseInt(resultSet.getString("catalogid"));
				name = resultSet.getString("catalogname");
				
				cataloglist.add(new Catalog(id,name));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getDbUrl() {
		// implement
		return DB_URL_PROD;
	}

	@Override
	public String getQuery() {
		// implement  Miki here
		switch(qtype){
		case READ_ALL:
			return read_query;
		case DELETE:
			return delete_query;
		case INSERT:
			return insert_query;
		default :
		return null;
				
		}
		
	}

	@Override
	public Object[] getQueryParams() {
		switch(qtype){
		
		case READ_ALL :
			return read_params;
		case DELETE :
			return delete_params;
		case INSERT:
			return insert_params;
			default:
		return null;
		
		}
		
	}

	@Override
	public int[] getParamTypes() {
		switch(qtype){
		case READ_ALL:
			return read_paramTypes;
		case DELETE:
			return delete_paramTypes;
		case INSERT:
			return insert_paramTypes;
		default:
			return null;
		}
	}

}
