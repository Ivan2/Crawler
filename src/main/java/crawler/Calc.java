package crawler;

import data.Data;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Calc {

	public static long[][] calcAverageCount(Data data) {
		int count = data.getDates().size();

		long[][] result = new long[7][3];
		long[] postCount = new long[7];

		Calendar now = new GregorianCalendar(TimeZone.getTimeZone("EAT"));
		now.setTimeInMillis(System.currentTimeMillis());

		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("EAT")); //московское время

		for (int i=0; i<count; i++){
			calendar.setTimeInMillis(data.getDates().get(i));

			if (calendar.get(Calendar.DATE) < now.get(Calendar.DATE) ||
					calendar.get(Calendar.DATE) >= now.get(Calendar.DATE)-8) {

				result[calendar.get(Calendar.DAY_OF_WEEK)-1][0]
						+= data.getComments().get(i);
				result[calendar.get(Calendar.DAY_OF_WEEK)-1][1]
						+= data.getLikes().get(i);
				result[calendar.get(Calendar.DAY_OF_WEEK)-1][2]
						+= data.getReposts().get(i);

				postCount[calendar.get(Calendar.DAY_OF_WEEK)-1]++;
			}

		}

		for (int i=0; i<7; i++) {
			result[i][0] /= postCount[i];
			result[i][1] /= postCount[i];
			result[i][2] /= postCount[i];
		}

		return result;
	}

}
