package data;

import java.util.List;

public class Data {

	private List<Long> comments;
	private List<Long> likes;
	private List<Long> reposts;
	private List<Long> dates;
	private long update;

	public Data(List<Long> comments, List<Long> likes, List<Long> reposts,
	            List<Long> dates, long update) {
		this.comments = comments;
		this.likes = likes;
		this.reposts = reposts;
		this.dates = dates;
		this.update = update;
	}

	public List<Long> getComments() {
		return comments;
	}

	public List<Long> getLikes() {
		return likes;
	}

	public List<Long> getReposts() {
		return reposts;
	}

	public List<Long> getDates() {
		return dates;
	}

	public long getUpdate() {
		return update;
	}
}
