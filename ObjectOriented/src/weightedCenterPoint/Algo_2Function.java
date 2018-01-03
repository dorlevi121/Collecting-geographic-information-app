package weightedCenterPoint;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import GPSPoints.GPSPoint;

/**
 * This class represents functions that read comb CSV and no GPS CSV,
 * calculate Algo2 and return to the Input file the user location
 * @author
 */
public class Algo_2Function {
	static final int NO_SIGNAL = -120;
	private List<List<Algo2_line>> _list; // the excel list for all MAC
	private List<Calculate_Algo2> _comb; //combined list for the final calculates
	private List<Line_Algo2> _input;
	private List<Line_Algo2> _data;
	private GPSPoint _point;
	private double w_alt, w_lon, w_lat;

	/**
	 * This function go over the Input list and search every MAC address in the Data list
	 * return the new location in the Input file
	 */
	public void search_Mac(){
		for(Line_Algo2 line_input : _input){
			_list = new ArrayList<List<Algo2_line>>();
			_comb = new ArrayList<Calculate_Algo2>();
			List<algo2Network> n = line_input.getListOfNetwork();
			for(algo2Network net : n){
				List<Algo2_line> l = new ArrayList<Algo2_line>();
				String mac_input = net.getMac();
				for(Line_Algo2 line_data : _data){
					List<algo2Network> _wifi = line_data.getListOfNetwork();
					for(algo2Network wifi : _wifi){
						if(wifi.getMac().equals(mac_input)){
							Algo2_line al = new Algo2_line(line_data,net.getSignal(), wifi.getSignal());
							l.add(al);
						}
						else{
							Algo2_line al =new Algo2_line(line_data,net.getSignal(),NO_SIGNAL);
							l.add(al);
						}
					}
				}
				_list.add(l);
			}
			int wifi_Number = _list.size();// the List<List> size
			int list_size = _list.get(0).size();//each list size

			for(int j=0;j<list_size;j++){
				double pi = 1.0;
				for(int i =0;i<wifi_Number;i++){
					List<Algo2_line> ls = _list.get(i);
					Algo2_line alg = ls.get(j);
					pi*=alg.get_weight();
					_point = alg.get_p();
				
				}
				_comb.add(new Calculate_Algo2(_point,pi));
			}
			_comb.sort(null);
			calc_Weight();
			line_input.setGPSPoint(new GPSPoint( w_lat,w_lon,w_alt));
		}
	}

	/**
	 * this function calculate the wLat,wLon,wAlt according to the demands
	 * and the final w_alt, w_lat, w_lon to create a new GPSPoint
	 */
	public void calc_Weight(){
		double sum_wAlt = 0, sum_wLon = 0, sum_wLat = 0, sum_Weight = 0;
		for (int i=0; i < 3 && i<_comb.size(); i++){

			sum_wAlt += _comb.get(i).getPoint().getAlt()*_comb.get(i).getPi();
			sum_wLon += _comb.get(i).getPoint().getLon()*_comb.get(i).getPi();
			sum_wLat += _comb.get(i).getPoint().getLat()*_comb.get(i).getPi();
			sum_Weight+= _comb.get(i).getPi();
		}
		w_alt=sum_wAlt/sum_Weight;
		w_lon=sum_wLon/sum_Weight;
		w_lat=sum_wLat/sum_Weight;
	}

	/**
	 * Reading from comb_no_gps file.
	 * @param fileName
	 * @return List
	 */
	public void readFile(String fileName){
		_input = new ArrayList<Line_Algo2>();
		try{
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String line="";
			String[] str = line.split(",");
			while ((line = br.readLine()) != null) {
				List<algo2Network> net = new ArrayList<algo2Network>();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm a");//12/05/17 11:48 AM
				Date time = sdf.parse(str[0]);
				for(int i=6;i<str.length;i+=4){
					algo2Network n = new algo2Network(str[i], str[i+1], Integer.parseInt(str[i+3]));//String SSID, String Mac, int signal
					net.add(n);
				}
				net.sort(null);
				if(str[2].equals("?") ||str[3].equals("?") ||str[4].equals("?")){
					GPSPoint point = new GPSPoint(0,0,0);
					_input.add(new Line_Algo2(time,str[1],point,Integer.parseInt(str[5]), net));//Date time, String modelID,GPSPoint Point_3D,int numOfNetworks, List<algo2Network> listOfnetwork
				}
				else{
					GPSPoint point = new GPSPoint(Double.parseDouble(str[2]),Double.parseDouble(str[3]),Double.parseDouble(str[4]));//double lat, double lon, double alt
					_input.add(new Line_Algo2(time,str[1],point,Integer.parseInt(str[5]), net));
				}
			}
			fr.close();
			br.close();
		}
		catch(IOException | ParseException ex) {
			System.out.print("Errorg reading file\n" + ex);
			System.exit(2);
		}
	}
	/**
	 * Reading from merge input file (my merge file not boaz)
	 * @param fileName
	 */
/*/	public void readFile2(String fileName){
		_data = new ArrayList<Line_Algo2>();
		try{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line=""; 
			br.readLine();   
			while ((line = br.readLine()) != null) {
				String[] str = line.split(",");
				List<Network> net = new ArrayList<Network>();
				Point_2D point = new Point_2D(Double.parseDouble(str[3]),Double.parseDouble(str[2]));
				Time time = new Time();
				time = time.set_Date(str[0]);
				for(int i=6;i<str.length;i+=4){ 
					Network n = new Network(str[i], str[i+1], Integer.parseInt(str[i+2]), str[i+3]);
					net.add(n);
				} 
				_data.add(new LineFile(time,str[1],point,Double.parseDouble(str[4]),Integer.parseInt(str[5]), net));
			}
			br.close();
		}
		catch(IOException ex) {
			System.out.print("Error Algo2 reading comb file\n" + ex);
			System.exit(2);
		}
	}

	/**
	 * This function writes the new CSV file
	 * @param output output CSV file name
	 */

	public void toCsv(String output){
		try{
			FileWriter fw = new FileWriter(output);
			BufferedWriter bw = new BufferedWriter(fw);
			for(Line_Algo2 l : _input){
				bw.write(l.toString().replace("[", "").replace("]", ""));
				bw.write("\n");
			}
			bw.close();
		}
		catch(IOException ex) {
			System.out.print("Error writing file\n" + ex);
		}
	}

	public static void main(String[] args) {
		Algo_2Function a = new Algo_2Function();
		a.readFile("C:\\Users\\dorle\\Desktop\\test1\\_comb_no_gps_ts1.csv");
	//	a.readFile2("_comb_all_BM3_.csv");
		a.search_Mac();
		a.toCsv("C:\\Users\\dorle\\Desktop\\test1\\complete_File_Algo_2.csv");
	}
}
