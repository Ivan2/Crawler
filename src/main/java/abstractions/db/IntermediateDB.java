package abstractions.db;

import abstractions.data.IPostInfo;

import java.util.List;

public abstract class IntermediateDB implements IDB {

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

	public abstract List<String> getIDList();

	public abstract int setCommentsLikesRepostsCount(IPostInfo postInfo);

	public abstract void addCommentsLikesRepostsCount(IPostInfo postInfo);

	public abstract List<IPostInfo> getPostInfoList();

}
