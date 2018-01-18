package main.java.SQL;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.omg.Messaging.SyncScopeHelper;

import de.micromata.opengis.kml.v_2_2_0.gx.TourControl;
import main.java.Filters.Filter;
import main.java.Filters.FilterTime;
import main.java.Filters.filterList;
import main.java.GPSPoints.GPSPoint;
import main.java.weightedCenterPoint.Line_Algo2;
import main.java.weightedCenterPoint.algo2Network;

import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MySQL {

	private static String _ip = "5.29.193.52";
	private static String _url = "jdbc:mysql://5.29.193.52:3306/oop_course_ariel";
	private static String _user = "oop1";
	private static String _password = "Lambda1();";
	private static Connection _con = null;
	private static String _tableName="";

	public static void main(String[] args) {
		List<Line_Algo2> _line1 =	test_101("5.29.193.52", "jdbc:mysql://"+"5.29.193.52"+":3306/oop_course_ariel","oop1","Lambda1();","ex4_db");
		Filter filter = null;
//		filter = new FilterGPS(34, 31, 37, 33);
//		List<Line_Algo2> filteredList = filterList.filterList1(_line1,filter);
//		//Sorting the filteredList by signal (WiFi is implementing Comparable)
//		weightedCenterPoint.Line_Algo2.toCsv(filteredList,"C:/Users/dorle/Desktop");
		
		filter = new FilterTime("2017-12-03 08:37:10", "2017-12-03 08:37:18");
		List<Line_Algo2> filteredList = filterList.filterList1(_line1,filter);
		//Sorting the filteredList by signal (WiFi is implementing Comparable)
		Line_Algo2.toCsv(filteredList,"C:/Users/dorle/Desktop");
		
		
		
		//		for (Line_Algo2 line_Algo2 : _line1) {
//			System.out.println(line_Algo2.toString());
//		}
		
	}


	public static List<Line_Algo2> test_101(String _ip,String _url,String _user, String _password, String _tableName) {
		Statement st = null;
		ResultSet rs = null;
		Connection _con = null;
		int max_id = -1;
		List<Line_Algo2> _line = new ArrayList<Line_Algo2>();

		try {  
			_con = DriverManager.getConnection(_url, _user, _password);
			st = _con.createStatement();

			PreparedStatement pst = _con.prepareStatement("SELECT * FROM "+_tableName);
			rs = pst.executeQuery();
			ResultSetMetaData rsmd= rs.getMetaData();
			while (rs.next()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date time = sdf.parse(rs.getString(2));
				String model = rs.getString(3);
				double lat = rs.getDouble(4);
				double lon = rs.getDouble(5);
				double alt = rs.getDouble(6);
				GPSPoint point = new GPSPoint(lat,lon,alt);
				int numNetworks= rs.getInt(7);
				List<algo2Network> _net = new ArrayList<algo2Network>();
				for (int i = 8; i < rsmd.getColumnCount() ; i+=2) {
					
					if(rs.getString(i) != null){
						
						_net.add(new algo2Network(rs.getString(i), rs.getInt(i+1)));
					}
				}
				_line.add(new Line_Algo2(time, model, point, numNetworks, _net));    
			}
		} catch (SQLException | ParseException ex) {
			System.out.println("eror");
			Logger lgr = Logger.getLogger(MySQL.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);

		} finally {
			try {
				if (rs != null) {rs.close();}
				if (st != null) { st.close(); }
				if (_con != null) { _con.close();  }
			} catch (SQLException ex) {
				System.out.println("eror2");

				Logger lgr = Logger.getLogger(MySQL.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
		return _line;
	}
}