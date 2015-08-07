package classes.db;

import abstractions.db.FinalDB;
import classes.crawler.Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class FinalPostgresQLDB extends FinalDB {

	private final String RESULT_TABLE_NAME = "result";
	private final String INFO_TABLE_NAME = "info";

	public Connection connection;
	private Statement statement;

	public void connect() {
		try {
			Class.forName("org.postgresql.Driver");

			connection = DriverManager.getConnection("jdbc:postgresql://"
							+ host + ":" + port + "/" + dbName,
							userName, password);

			statement = connection.createStatement();

		} catch (ClassNotFoundException e){
			Control.error(getClass().getName(), e.toString());
		} catch (SQLException e){
			Control.error(getClass().getName(), e.toString());
		}
	}

	@Override
	public int setAverageDataOfDayOfWeek(byte dayOfWeek,
	                                     long averageCommentsCount,
	                                     long averageLikesCount,
	                                     long averageRepostsCount) {
		try {
			String setTableSQL = "UPDATE " + RESULT_TABLE_NAME
					+ " SET average_comments_count='" + averageCommentsCount
					+ "' WHERE day_of_week='" + dayOfWeek + "'";
			statement.executeUpdate(setTableSQL);

			setTableSQL = "UPDATE " + RESULT_TABLE_NAME
					+ " SET average_likes_count='" + averageLikesCount
					+ "' WHERE day_of_week='" + dayOfWeek + "'";
			statement.executeUpdate(setTableSQL);

			setTableSQL = "UPDATE " + RESULT_TABLE_NAME
					+ " SET average_reposts_count='" + averageRepostsCount
					+ "' WHERE day_of_week='" + dayOfWeek + "'";
			int updateCount = statement.executeUpdate(setTableSQL);

			return updateCount;

		} catch (SQLException e) {
			Control.error(getClass().getName(), e.toString());
		}

		return 0;
	}

	@Override
	public void addAverageDataOfDayOfWeek(byte dayOfWeek,
	                                      long averageCommentsCount,
	                                      long averageLikesCount,
	                                      long averageRepostsCount) {
		try {
			String insertTableSQL = "INSERT INTO " + RESULT_TABLE_NAME
					+ "(day_of_week, average_comments_count, average_likes_count, "
					+ "average_reposts_count) " + " VALUES"
					+ "(" + dayOfWeek + ", " + averageCommentsCount + ", "
					+ averageLikesCount + ", " + averageRepostsCount + ")";
			statement.executeUpdate(insertTableSQL);

		} catch (SQLException e) {
			Control.error(getClass().getName(), e.toString());
		}
	}

	@Override
	public int setUpdateInfo(String update) {
		try {
			String setTableSQL = "UPDATE " + INFO_TABLE_NAME
					+ " SET value='" + update
					+ "' WHERE info='update'";
			int updateCount = statement.executeUpdate(setTableSQL);

			return updateCount;

		} catch (SQLException e) {
			Control.error(getClass().getName(), e.toString());
		}

		return 0;
	}

	@Override
	public void addUpdateInfo(String update) {
		try {
			String insertTableSQL = "INSERT INTO " + INFO_TABLE_NAME
					+ "(info, value)" + " VALUES"
					+ "('update', '" + update + "')";
			statement.executeUpdate(insertTableSQL);

		} catch (SQLException e) {
			Control.error(getClass().getName(), e.toString());
		}
	}

	@Override
	public int setPostCountInfo(long postCount) {
		try {
			String setTableSQL = "UPDATE " + INFO_TABLE_NAME
					+ " SET value='" + postCount
					+ "' WHERE info='post_count'";
			int updateCount = statement.executeUpdate(setTableSQL);

			return updateCount;

		} catch (SQLException e) {
			Control.error(getClass().getName(), e.toString());
		}

		return 0;
	}

	@Override
	public void addPostCountInfo(long postCount) {
		try {
			String insertTableSQL = "INSERT INTO " + INFO_TABLE_NAME
					+ "(info, value)" + " VALUES"
					+ "('post_count', '" + postCount + "')";
			statement.executeUpdate(insertTableSQL);

		} catch (SQLException e) {
			Control.error(getClass().getName(), e.toString());
		}
	}

}
