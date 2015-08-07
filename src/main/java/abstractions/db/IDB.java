package abstractions.db;

public interface IDB {

	void setParams(String host, String port, String dbName,
	               String userName, String password);

	void connect();

}
