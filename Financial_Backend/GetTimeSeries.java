import java.util.regex.Pattern;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

//Returns an array of two ArrayLists, in the form [[price series],[volume series]]
public class GetTimeSeries {  

	 static int fromD;
	 static int fromM;
	 static int fromY;
	 static int toD;
	 static int toM;
	 static int toY;

	 public static void setTime(){

		 	DateFormat calFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		 	Calendar cal = Calendar.getInstance();
			String dateString = calFormat.format(cal.getTime());

			toD = Integer.parseInt(dateString.substring(0,2));
			toM = Integer.parseInt(dateString.substring(3,5));
			toY = Integer.parseInt(dateString.substring(6,8));

			fromD = toD;
			fromM = toM;
			fromY = toY - 1; //leaves 365 days betwee two data requests, allows for days when the market is not open to be compensated for
			
	 }

   public static ArrayList<ArrayList<Double>> getTimeSeries(String ticker){

		  setTime();

	 		try { 
				Double[] historic = new Double[100];

				//Retrieve CSV file for time series
				URL yahoo = new URL("http://ichart.finance.yahoo.com/table.csv?s=" + ticker + "&a=" + fromM + "&b=" 
					+ fromD + "&c=" + fromY + "&d=" + toM +"&e=" + toD + "&f=" + toY + "&g=d&ignore=.csv");
				URLConnection connection = yahoo.openConnection(); 
				InputStreamReader is = new InputStreamReader(connection.getInputStream());
				BufferedReader br = new BufferedReader(is);  
				
				ArrayList<String> temp = new ArrayList<String>();
				int i = 0;
				while(i<101){ //note this is 101 because we want 100 data points and the first line is the heading
					temp.add(br.readLine());
					i++;
				}
				temp.remove(0);
				//Only split on commas that aren't in quotes
				ArrayList<Double> prices = new ArrayList<Double>();
				ArrayList<Double> volumes = new ArrayList<Double>();
				//System.out.println(temp.toString());
				for(String v:temp){
					if(v != null){
						String[] buff = v.split(",");
						prices.add(Double.parseDouble(buff[4]));
						volumes.add(Double.parseDouble(buff[5]));
					}
				}

				//System.out.println(Integer.toString(prices.size()));
				//System.out.println(Integer.toString(volumes.size()));

				ArrayList<ArrayList<Double>> out = new ArrayList<ArrayList<Double>>();
				out.add(prices);
				out.add(volumes);
	
				return out;

			} catch (IOException e) {
					Logger log = Logger.getLogger(StockFetcher.class.getName()); 
					log.log(Level.SEVERE, e.toString(), e);
					return null;
				}
   }

   public static void main(String[] args){
		 getTimeSeries("AAPL");
		 setTime();
   }
}