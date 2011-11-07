package objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtils {
	static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";

	public static Date GetUTCdatetimeAsDate()
	{
		//note: doesn't check for null
		return StringDateToDate(GetUTCdatetimeAsString());
	}

	public static String GetUTCdatetimeAsString()
	{
		final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		final String utcTime = sdf.format(new Date());

		return utcTime;
	}

	public static Date StringDateToDate(String StrDate)
	{
		Date dateToReturn = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATEFORMAT);

		try
		{
			dateToReturn = (Date)dateFormat.parse(StrDate);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}

		return dateToReturn;
	}
}
