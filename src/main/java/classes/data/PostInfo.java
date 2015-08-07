package classes.data;

import abstractions.data.IPostInfo;

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class PostInfo implements IPostInfo {

	private long id;
	private long date;
	private long commentsCount;
	private long likesCount;
	private long repostsCount;

	public void setParams(long id, long date, long commentsCount,
	                      long likesCount, long repostsCount) {
		this.id = id;
		this.date = date;
		this.commentsCount = commentsCount;
		this.likesCount = likesCount;
		this.repostsCount = repostsCount;
	}

	public byte[] toByteArray() {
		byte[] result = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baos);
			dos.writeLong(id);
			dos.writeLong(date);
			dos.writeLong(commentsCount);
			dos.writeLong(likesCount);
			dos.writeLong(repostsCount);
			result = baos.toByteArray();
			dos.close();
			baos.close();
		} catch (IOException e) {}
		return result;
	}

	public void parseByteArray(byte[] bytes) {
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			DataInputStream dis = new DataInputStream(bais);
			id = dis.readLong();
			date = dis.readLong();
			commentsCount = dis.readLong();
			likesCount = dis.readLong();
			repostsCount = dis.readLong();

		} catch (IOException e) {}
	}

	private byte calcDayOfWeek() {
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("EAT")); //московское время
		calendar.setTimeInMillis(date);
		return (byte)(calendar.get(Calendar.DAY_OF_WEEK)-1);
	}

	public long getId() {
		return id;
	}

	public long getDate() {
		return date;
	}

	public long getLikesCount() {
		return likesCount;
	}

	public long getCommentsCount() {
		return commentsCount;
	}

	public long getRepostsCount() {
		return repostsCount;
	}

	public byte getDayOfWeek() {
		return calcDayOfWeek();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PostInfo))
			return false;
		PostInfo postInfo = (PostInfo)obj;
		if (id == postInfo.id && date == postInfo.date &&
				commentsCount == postInfo.commentsCount &&
				likesCount == postInfo.likesCount &&
				repostsCount == postInfo.repostsCount)
			return true;
		return false;
	}

}
