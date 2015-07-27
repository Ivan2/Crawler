package db;

import data.Data;
import load.ILoadDataManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoadDBManager implements ILoadDataManager {

	private final String RESULT_TABLE_NAME = "result";
	private final String UPDATE_TABLE_NAME = "update";

	private Connection connection;
	private Statement statement;

	private void connect() {
		try {
			Class.forName("org.postgresql.Driver");

			String host = System.getenv("OPENSHIFT_POSTGRESQL_DB_HOST");
			String port = System.getenv("OPENSHIFT_POSTGRESQL_DB_PORT");
			String userName = System.getenv("OPENSHIFT_POSTGRESQL_DB_USERNAME");
			String password = System.getenv("OPENSHIFT_POSTGRESQL_DB_PASSWORD");

			connection = DriverManager.getConnection("jdbc:postgresql://" + host
							+ ":" + port + "/crawler", userName, password);

			statement = connection.createStatement();

		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	public List<Long> getLongDataList(String columnName, String tableName) {
		try {
			ArrayList<Long> result = new ArrayList<Long>();
			String selectTableSQL = "SELECT "+columnName+" from " + tableName;
			ResultSet set = statement.executeQuery(selectTableSQL);

			while (set.next()) {
				result.add(set.getLong(columnName));
			}

			return result;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Data loadData() {
		connect();

		List<Long> comments = getLongDataList("comments", RESULT_TABLE_NAME);
		List<Long> likes = getLongDataList("likes", RESULT_TABLE_NAME);
		List<Long> reposts = getLongDataList("reposts", RESULT_TABLE_NAME);
		List<Long> dates = getLongDataList("date", RESULT_TABLE_NAME);
		long update = getLongDataList("date", UPDATE_TABLE_NAME).get(0);

		return new Data(comments, likes, reposts, dates, update);
	}
}
