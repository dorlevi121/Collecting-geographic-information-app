package Filters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Wifi.WiFi;
import weightedCenterPoint.Line_Algo2;
/**
* This class compare between 2 wifi by Time
* @author Dor Levi, Yarden Mizrahi
*/

public class FilterTime implements Filter {
	Date startTime;
	Date endTime ;
	/**
	 * FilterTime builder 
	 * * @param StartTime
	 * @param  EndTime 
	 */
	public FilterTime(String StartTime, String EndTime){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.startTime = sdf.parse(StartTime);
			this.endTime = sdf.parse(EndTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	/**
	* This function compare between 2 wifi by time
	* @param wifi wifi object
	* @return true if the time points same to the other wifi
	*/
	@Override
	public boolean isBelong(WiFi wifi) {
			return (startTime.before(wifi.getTime()) && endTime.after(wifi.getTime()));
	}
	@Override
	public boolean isBelongsql(Line_Algo2 wifi) {
		return (startTime.before(wifi.getTime()) && endTime.after(wifi.getTime()));
		
	}
	

}
