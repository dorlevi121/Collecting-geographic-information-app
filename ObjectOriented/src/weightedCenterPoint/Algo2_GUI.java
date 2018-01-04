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
import java.util.Scanner;

import Filters.FilterMAC;
import Filters.filterList;
import weightedCenterPoint.Algo_2Function;

import GPSPoints.GPSPoint;
import Wifi.WiFi;



public class Algo2_GUI {
	static final int NO_SIGNAL = -120;
	private List<List<Algo2_line>> _list; // the excel list for all MAC
	private List<Calculate_Algo2> _comb; //combined list for the final calculates
	private List<Line_Algo2> _input;
	algo2Network[] macAndSignal;
	private List<Line_Algo2> _data;
	private GPSPoint _point;
	private double w_alt, w_lon, w_lat;


	public void readCombAllFile(String fileName){
		_data = new ArrayList<Line_Algo2>();
		try{
			FileReader Fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(Fr);
			String line = br.readLine(); 
			String[] str = line.split(",");  
			while (line  != null) {
				List<algo2Network> net = new ArrayList<algo2Network>();
				GPSPoint point1 = new GPSPoint(Double.parseDouble(str[2]),Double.parseDouble(str[3]),Double.parseDouble(str[4]));
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//2017-12-03 08:53:08
				Date time1 = sdf1.parse(str[0]);
				for(int i=6;i<str.length;i+=4){ 
					algo2Network n = new algo2Network( str[i+1],( (int)(Double.parseDouble(str[i+3]))));//String SSID, String Mac, int signal
					net.add(n);
				} 
				_data.add(new Line_Algo2(time1,str[1],point1,Integer.parseInt(str[5]), net));
				line=br.readLine();//Date time, String modelID,GPSPoint Point_3D,int numOfNetworks, List<algo2Network> listOfnetwork
			}
			br.close();
		}
		catch(IOException | ParseException ex) {
			System.out.print("Error Algo2 reading comb file\n"+ex );
			System.exit(2);
		}
	}

	public void search_MacAndSignal(){
		for(Line_Algo2 line_input : _data){
			_list = new ArrayList<List<Algo2_line>>();
			_comb = new ArrayList<Calculate_Algo2>();
			List<algo2Network> n = line_input.getListOfNetwork();
			for(algo2Network net : n){
				List<Algo2_line> l = new ArrayList<Algo2_line>();
				String mac_input = net.getMac();
				//List<algo2Network> _wifi =new ArrayList<>() macAndSignal;
				for (int i = 0; i < macAndSignal.length; i++) {
					if(macAndSignal[i].getMac().equals(mac_input)){
						Algo2_line al = new Algo2_line(line_input,net.getSignal(), macAndSignal[i].getSignal());
						l.add(al);
					}
					else{
						Algo2_line al =new Algo2_line(line_input,net.getSignal(),NO_SIGNAL);
						l.add(al);
					}
				}
			/*/	for(algo2Network wifi : macAndSignal){
					if(wifi!=null &&(wifi.getMac().equals(mac_input))){
						Algo2_line al = new Algo2_line(line_input,net.getSignal(), wifi.getSignal());
						l.add(al);
					}
			/*/		
				
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
					_point = alg.get_point();

				}
				_comb.add(new Calculate_Algo2(_point,pi));
			}
			_comb.sort(null);
			calc_Weight();
			_point= new GPSPoint(w_lat,w_lon, w_alt);
		}


	public void calc_Weight(){
		double sum_wAlt = 0, sum_wLon = 0, sum_wLat = 0, sum_Weight = 0;
		for (int i=0; i < 2 && i<_comb.size(); i++){

			sum_wAlt += _comb.get(i).getPoint().getAlt()*_comb.get(i).getPi();
			sum_wLon += _comb.get(i).getPoint().getLon()*_comb.get(i).getPi();
			sum_wLat += _comb.get(i).getPoint().getLat()*_comb.get(i).getPi();
			sum_Weight+= _comb.get(i).getPi();
		}
		w_alt=sum_wAlt/sum_Weight;
		w_lon=sum_wLon/sum_Weight;
		w_lat=sum_wLat/sum_Weight;
	}
	public void toCsv(String output){

		try{
			FileWriter fw = new FileWriter(output);
			BufferedWriter bw = new BufferedWriter(fw);
			String ColumnNamesList = "w_Lat,w_Lon,w_Alt";
			bw.append(","+ColumnNamesList + "\n");
			bw.append(","+_point.getLat()+","+_point.getLon()+","+_point.getAlt());
			bw.close();
		}
		catch(IOException ex) {
			System.out.print("Error writing file\n" + ex);
		}
	}




	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//List<algo2Network> macAndSignal = new ArrayList<algo2Network>();
		algo2Network[] macAndSignal =new algo2Network [3];
		algo2Network a1= new algo2Network("00:1a:dd:e3:06:e4", -78);
		algo2Network a2= new algo2Network("14:ae:db:43:88:35", -84);
		algo2Network a3= new algo2Network("1c:b9:c4:16:f4:78", -68);
		macAndSignal[0]=a1;macAndSignal[1]=a2;macAndSignal[2]=a3;
	
		Algo2_GUI a = new Algo2_GUI();
		a.read
		a.search_MacAndSignal();
		a.toCsv("C:\\Users\\Yarden\\Downloads\\testing (1)\\testing\\complete_File_Algo_2-BM2_ts1.csv");
		System.out.println("done");

	}


}
