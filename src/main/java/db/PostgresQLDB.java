package db;

import settings.Settings;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresQLDB implements IDB {

	private final String RESULT_TABLE_NAME = "result";
	private final String GROUP_ID_TABLE_NAME = "group_id";
	private final String UPDATE_TABLE_NAME = "update";

	public Connection connection;
	private Statement statement;

	private String host;
	private String port;
	private Settings settings;

	public PostgresQLDB(String host, String port, Settings settings) {
		this.host = host;
		this.port = port;
		this.settings = settings;
	}

	private void checkConnection() {
		if (!isValid()) {
			port = PortForwardManager.createConnection();
			connect();
		}
	}

	public void connect() {
		try {
			Class.forName("org.postgresql.Driver");

			connection = DriverManager.getConnection("jdbc:postgresql://" + host
					+ ":" + port + "/" + settings.getAppName(),
					settings.getDbUserName(), settings.getDbPassword());

			statement = connection.createStatement();

		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	public List<String> getIDList() {
		checkConnection();
		try {
			ArrayList<String> result = new ArrayList<String>();
			String selectTableSQL = "SELECT id from " + GROUP_ID_TABLE_NAME;
			ResultSet set = statement.executeQuery(selectTableSQL);

			while (set.next()) {
				result.add(set.getString("id"));
			}

			return result;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int setData(long id, long newComments, long newLikes,
	                   long newReposts, long newDate) {
		checkConnection();
		try {
			String setTableSQLComments = "UPDATE " + RESULT_TABLE_NAME
					+ " set comments = '" + newComments + "' where id='"
					+ id + "'";
			int comments = statement.executeUpdate(setTableSQLComments);

			String setTableSQLLikes = "UPDATE " + RESULT_TABLE_NAME
					+ " set likes = '" + newLikes + "' where id='" + id + "'";
			int likes = statement.executeUpdate(setTableSQLLikes);

			String setTableSQLReposts = "UPDATE " + RESULT_TABLE_NAME
					+ " set reposts = '" + newReposts + "' where id='"
					+ id + "'";
			int reposts = statement.executeUpdate(setTableSQLReposts);

			String setTableSQLText = "UPDATE " + RESULT_TABLE_NAME
					+ " set date = '" + newDate + "' where id='" + id + "'";
			int date = statement.executeUpdate(setTableSQLText);

			if (comments != likes || comments != reposts || comments != date)
				return -1;

			return comments;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	public void addData(long id, long comments, long likes, long reposts,
	                    long date) {
		checkConnection();
		try {
			String insertTableSQL = "INSERT INTO " + RESULT_TABLE_NAME
					+ "(id, comments, likes, reposts, date) " + "VALUES"
					+ "(" + id + ", " + comments + ", " + likes + ", "
					+ reposts + ", " + date + ")";
			statement.executeUpdate(insertTableSQL);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int setDateOfUpdate() {
		checkConnection();
		try {
			String setTableSQLDate = "UPDATE " + UPDATE_TABLE_NAME + " set date = '"
					+ new java.util.Date().getTime() + "' where info='date'";

			return statement.executeUpdate(setTableSQLDate);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	public void addDateOfUpdate() {
		checkConnection();
		try {
			String insertTableSQL = "INSERT INTO " + UPDATE_TABLE_NAME
					+ "(info, date) " + "VALUES"
					+ "('date', " + new java.util.Date().getTime() + ")";
			statement.executeUpdate(insertTableSQL);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isValid() {
		boolean valid = false;
		try {
			if (!connection.isClosed()) {
				Statement stm = connection.createStatement();
				stm.executeQuery( "SELECT 1" ).close();
				valid = true;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}

		return valid;
	}

	/*public void delete(long id) {
		try {
			String deleteTableSQL = "DELETE FROM result WHERE id='"+id+"'";
			statement.execute(deleteTableSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/

}
