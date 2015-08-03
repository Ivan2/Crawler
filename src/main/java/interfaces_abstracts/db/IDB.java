package interfaces_abstracts.db;

public interface IDB {

	void setParams(String host, String port, String dbName,
	               String userName, String password);

	void connect();

}
