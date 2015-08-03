package db;

import data.AverageCount;
import data.Info;
import load.ILoadDataManager;

import java.sql.*;

public class PostgresQLManager implements ILoadDataManager {

	private final String RESULT_TABLE_NAME = "result";
	private final String INFO_TABLE_NAME = "info";

	private Connection connection;
	private Statement statement;

	public void connect() {
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

	public AverageCount[] loadAverageCount() {
		AverageCount[] result = new AverageCount[7];
		try {
			String selectTableSQL = "SELECT * FROM " + RESULT_TABLE_NAME;
			ResultSet set = statement.executeQuery(selectTableSQL);

			for (int i=0; i<7; i++)
				if (set.next()) {
					long averageCommentsCount = set.getLong("average_comments_count");
					long averageLikesCount = set.getLong("average_likes_count");
					long averageRepostsCount = set.getLong("average_reposts_count");

					result[i] = new AverageCount(averageCommentsCount,
							averageLikesCount, averageRepostsCount);
				}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Info loadInfo() {
		Info result = null;
		try {
			String update = null;
			String postCount = null;

			String selectTableSQL = "SELECT value FROM " + INFO_TABLE_NAME
					+ " WHERE info='update'";
			ResultSet set = statement.executeQuery(selectTableSQL);

			if (set.next())
				update = set.getString("value");

			selectTableSQL = "SELECT value FROM " + INFO_TABLE_NAME
					+ " WHERE info='post_count'";
			set = statement.executeQuery(selectTableSQL);

			if (set.next())
				postCount = set.getString("value");

			result = new Info(update, postCount);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
