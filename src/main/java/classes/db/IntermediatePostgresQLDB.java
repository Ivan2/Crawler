package classes.db;

import classes.crawler.Control;
import classes.ioc.IoCAdapter;
import interfaces_abstracts.data.IPostInfo;
import interfaces_abstracts.db.IntermediateDB;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class IntermediatePostgresQLDB extends IntermediateDB {

	private final String GROUP_ID_TABLE_NAME = "group_id";
	private final String POST_INFO_TABLE_NAME = "post_info";

	public Connection connection;
	public Statement statement;

	public void connect() {
		try {
			Class.forName("org.postgresql.Driver");

			connection = DriverManager.getConnection("jdbc:postgresql://"
							+ host + ":" + port + "/" + dbName,
							userName, password);

			statement = connection.createStatement();

		} catch (ClassNotFoundException e){
			Control.log(e.toString());
		} catch (SQLException e){
			Control.log(e.toString());
		}
	}

	@Override
	public List<String> getIDList() {
		try {
			LinkedList<String> result = new LinkedList<String>();
			String selectTableSQL = "SELECT id from " + GROUP_ID_TABLE_NAME;
			ResultSet set = statement.executeQuery(selectTableSQL);

			while (set.next()) {
				result.add(set.getString("id"));
			}

			return result;

		} catch (SQLException e) {
			Control.log(e.toString());
		}
		return null;
	}

	@Override
	public int setCommentsLikesRepostsCount(IPostInfo postInfo) {
		try {
			String setTableSQL = "UPDATE " + POST_INFO_TABLE_NAME
					+ " SET comments_count = '" + postInfo.getCommentsCount() + "'"
					+ " WHERE id='" + postInfo.getId()
					+ "' AND date='" + postInfo.getDate() + "'";
			statement.executeUpdate(setTableSQL);

			setTableSQL = "UPDATE " + POST_INFO_TABLE_NAME
					+ " SET likes_count = '" + postInfo.getLikesCount() + "'"
					+ " WHERE id='" + postInfo.getId()
					+ "' AND date='" + postInfo.getDate() + "'";
			statement.executeUpdate(setTableSQL);


			setTableSQL = "UPDATE " + POST_INFO_TABLE_NAME
					+ " SET reposts_count = '" + postInfo.getRepostsCount() + "'"
					+ " WHERE id='" + postInfo.getId()
					+ "' AND date='" + postInfo.getDate() + "'";
			int updateCount = statement.executeUpdate(setTableSQL);

			return updateCount;

		} catch (SQLException e) {
			Control.log(e.toString());
		}

		return 0;
	}

	@Override
	public void addCommentsLikesRepostsCount(IPostInfo postInfo) {
		try {
			String insertTableSQL = "INSERT INTO " + POST_INFO_TABLE_NAME
					+ "(id, date, comments_count, likes_count, reposts_count) " + " VALUES"
					+ "(" + postInfo.getId() + ", " + postInfo.getDate() + ", "
					+ postInfo.getCommentsCount() + ", "
					+ postInfo.getLikesCount() + ", "
					+ postInfo.getRepostsCount() + ")";

			statement.executeUpdate(insertTableSQL);

		} catch (SQLException e) {
			Control.log(e.toString());
		}
	}

	@Override
	public List<IPostInfo> getPostInfoList() {

		LinkedList<IPostInfo> result = new LinkedList<IPostInfo>();

		try {
			String selectTableSQL = "SELECT * FROM " + POST_INFO_TABLE_NAME;
			ResultSet set = statement.executeQuery(selectTableSQL);

			while (set.next()) {
				long id = set.getLong("id");
				long date = set.getLong("date");
				long commentsCount = set.getLong("comments_count");
				long likesCount = set.getLong("likes_count");
				long repostsCount = set.getLong("reposts_count");

				IPostInfo postInfo = IoCAdapter.getInstance().getIPostInfoObject();
				postInfo.setParams(id, date, commentsCount, likesCount, repostsCount);
				result.add(postInfo);
			}

		} catch (SQLException e) {
			Control.log(e.toString());
		}

		return result;
	}

}
