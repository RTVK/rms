package com.rms.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.joda.time.DateTime;

public class DateUtils {

	public static Date getSqlDateFromString(final String dateTime) {
		return new Date(DateTime.parse(dateTime).getMillis());
	}

	public static DateTime getJodaDateFromTimestamp(final Timestamp timestamp) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		return DateTime.parse(simpleDateFormat.format(new Date(timestamp.getTime())));
	}
}
