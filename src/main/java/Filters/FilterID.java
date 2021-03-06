package main.java.Filters;
import main.java.Wifi.WiFi;
import main.java.weightedCenterPoint.Line_Algo2;
/**
* This class compare between 2 wifi by ID
* @author Dor Levi, Yarden Mizrahi
*/
public class FilterID  implements Filter{
		public String ID;
		public FilterID (String ID){
			this.ID = new String(ID);
		}
		/**
		* This function compare between 2 wifi by ID
		* @param wifi object
		* @return true if the ID points same to the other wifi
		*/
		@Override
		public boolean isBelong(WiFi wifi) {
			if(ID.equals(wifi.getModelID()))
				return true;
			
			return false;
		}
		@Override
		public boolean isBelongsql(Line_Algo2 wifi) {
			if(ID.equals(wifi.getModelID()))
				return true;
			
			return false;
		}
		
	

}
