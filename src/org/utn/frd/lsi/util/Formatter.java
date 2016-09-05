package org.utn.frd.lsi.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatter {
	
	private static final String STATISTIC_DATE = "yyyyMMdd";
	
	public static String formatStatisticDate(Date date){
		return (new SimpleDateFormat( STATISTIC_DATE )).format(date);
	}
}
