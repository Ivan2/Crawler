package classes.date;

import interfaces_abstracts.date.IDateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime implements IDateTime {

	public String now() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy  HH:mm:ss z");
		return dateFormat.format(new Date());
	}
}
