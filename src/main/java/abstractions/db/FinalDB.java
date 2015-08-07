package abstractions.db;

public abstract class FinalDB implements IDB {

	protected String host;
	protected String port;
	protected String dbName;
	protected String userName;
	protected String password;

	public void setParams(String host, String port, String dbName,
	                      String userName, String password) {
		this.host = host;
		this.port = port;
		this.dbName = dbName;
		this.userName = userName;
		this.password = password;
	}

	public abstract int setAverageDataOfDayOfWeek(byte dayOfWeek,
	                                     long averageCommentsCount,
	                                     long averageLikesCount,
	                                     long averageRepostsCount);

	public abstract void addAverageDataOfDayOfWeek(byte dayOfWeek,
	                              long averageCommentsCount,
	                              long averageLikesCount,
	                              long averageRepostsCount);

	public abstract int setUpdateInfo(String update);

	public abstract void addUpdateInfo(String update);

	public abstract int setPostCountInfo(long postCount);

	public abstract void addPostCountInfo(long postCount);

}
