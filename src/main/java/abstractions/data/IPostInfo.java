package abstractions.data;

public interface IPostInfo {

	void setParams(long id, long date, long commentsCount, long likesCount,
	               long repostsCount);

	byte[] toByteArray();

	void parseByteArray(byte[] bytes);

	long getId();

	long getDate();

	long getLikesCount();

	long getCommentsCount();

	long getRepostsCount();

	byte getDayOfWeek();

}
