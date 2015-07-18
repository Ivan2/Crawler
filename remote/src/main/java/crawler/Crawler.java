package crawler;

import net.DataLoader;
import net.IDataLoader;

public class Crawler {
	
	private byte[] postCount;
	
	public Crawler() {
		IDataLoader dataLoader = new DataLoader();
		postCount = dataLoader.load();
	}
	
	public byte getMondayPostCount() {
		return postCount[1];
	}

	public byte getTuesdayPostCount() {
		return postCount[2];
	}

	public byte getWednesdayPostCount() {
		return postCount[3];
	}

	public byte getThursdayPostCount() {
		return postCount[4];
	}

	public byte getFridayPostCount() {
		return postCount[5];
	}

	public byte getSaturdayPostCount() {
		return postCount[6];
	}

	public byte getSundayPostCount() {
		return postCount[0];
	}
	
}
