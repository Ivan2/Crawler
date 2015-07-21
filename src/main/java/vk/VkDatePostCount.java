package vk;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class VkDatePostCount {

	public static byte[] calcPostCount(long[] dates) {
		byte[] postCount = new byte[7];
		Calendar now = new GregorianCalendar(TimeZone.getTimeZone("EAT"));
		now.setTimeInMillis(System.currentTimeMillis());
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("EAT")); //московское время
		for (long date : dates){
			calendar.setTimeInMillis(date);
			if (calendar.get(Calendar.DATE) < now.get(Calendar.DATE) ||
					calendar.get(Calendar.DATE) >= now.get(Calendar.DATE)-8)
			postCount[calendar.get(Calendar.DAY_OF_WEEK)-1]++;
		}
		return postCount;
	}

}
