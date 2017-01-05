import java.util.regex.Pattern;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GetTimeSeries {  
   public static Double[] getTimeSeries(String ticker,int fromMonth,int fromDay,int fromYear,int toMonth,int toDay,int toYear){
   
      Double[] historic = new Double[100];

			//Retrieve CSV file for time series
			URL yahoo = new URL("http://ichart.finance.yahoo.com/table.csv?s=" + ticker + "&a=11&b=15&c=2016&d=11&e=19&f=2016&g=d&ignore=.csv");
			URLConnection connection = yahoo.openConnection(); 
			InputStreamReader is = new InputStreamReader(connection.getInputStream());
			BufferedReader br = new BufferedReader(is);  
			
			// Parse CSV Into Array
			String line = br.readLine(); 
			//Only split on commas that aren't in quotes
			String[] stockinfo = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

      System.out.println(stockinfo);

   }

   public static void main(String[] args){
     getTimeSeries("AAPL",1,1,2016,1,1,2017);
   }
}