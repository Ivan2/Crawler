package db;

import java.util.List;

public interface IDB {

	void connect();

	int setData(long id, long newComments, long newLikes, long newReposts,
	            long newDate);

	void addData(long id, long comments, long likes, long reposts, long date);

	List<String> getIDList();

	int setDateOfUpdate();

	void addDateOfUpdate();

	boolean isValid();

	//void delete(long id);

}
