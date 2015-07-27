package load_wall.wall_data;

public class Data {

	private long[] id;
	private long[] comments;
	private long[] likes;
	private long[] reposts;
	private long[] dates;

	public Data(long[] id, long[] comments, long[] likes, long[] reposts, long[] dates){
		this.id = id;
		this.comments = comments;
		this.likes = likes;
		this.reposts = reposts;
		this.dates = dates;
	}

	public long[] getId(){
		return id;
	}

	public long[] getComments(){
		return comments;
	}

	public long[] getLikes(){
		return likes;
	}

	public long[] getReposts(){
		return reposts;
	}

	public long[] getDates() {
		return dates;
	}
}

