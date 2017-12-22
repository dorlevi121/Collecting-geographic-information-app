package Filters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Wifi.WiFi;


public class FilterTime implements Filter {
	Date startTime;
	Date endTime ;
	
	public FilterTime(String StartTime, String EndTime){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.startTime = sdf.parse(StartTime);
			this.endTime = sdf.parse(EndTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean isBelong(WiFi wifi) {
			return (startTime.before(wifi.getTime()) && endTime.after(wifi.getTime()));
	}
	

}
