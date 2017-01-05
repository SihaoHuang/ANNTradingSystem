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

public class GetTimeSeries {  
   public static Double[] getTimeSeries(String ticker){
	 		try { 
				Double[] historic = new Double[100];

				//Retrieve CSV file for time series
				URL yahoo = new URL("http://ichart.finance.yahoo.com/table.csv?s=" + ticker + "&a=5&b=15&c=2016&d=11&e=19&f=2016&g=d&ignore=.csv");
				URLConnection connection = yahoo.openConnection(); 
				InputStreamReader is = new InputStreamReader(connection.getInputStream());
				BufferedReader br = new BufferedReader(is);  
				
				ArrayList<String> temp = new ArrayList<String>();
				int i = 0;
				while(i<101){ //note this is 101 because we want 100 data points and the first line is the headig
					temp.add(br.readLine());
					i++;
				}
				temp.remove(0);
				//Only split on commas that aren't in quotes
				ArrayList<Double> prices = new ArrayList<Double>();

ArrayList<Double> prices = new ArrayList<Double>();
				//",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"
				System.out.println(temp.toString());

				return historic;

			} catch (IOException e) {
					Logger log = Logger.getLogger(StockFetcher.class.getName()); 
					log.log(Level.SEVERE, e.toString(), e);
					return null;
				}
   }


   public static void main(String[] args){
     getTimeSeries("AAPL");
   }
}