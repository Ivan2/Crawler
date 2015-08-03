package data;

public class AverageCount {

	private long commentsCount;
	private long likesCount;
	private long repostsCount;

	public AverageCount(long commentsCount, long likesCount, long repostsCount) {
		this.commentsCount = commentsCount;
		this.likesCount = likesCount;
		this.repostsCount = repostsCount;
	}

	public long getCommentsCount() {
		return commentsCount;
	}

	public long getLikesCount() {
		return likesCount;
	}

	public long getRepostsCount() {
		return repostsCount;
	}

}
