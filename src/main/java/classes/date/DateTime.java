package classes.date;

import abstractions.date.IDateTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTime implements IDateTime {

	public String now() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy  HH:mm:ss z");
		dateFormat.getCalendar().setTimeZone(TimeZone.getTimeZone("EAT"));
		return dateFormat.format(new Date());
	}
}
