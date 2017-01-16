package financialdata;

import java.util.regex.Pattern;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class StockFetcher {  
	
	public static NormalStock getStock(String symbol) {  
		String sym = symbol.toUpperCase();
		double price = 0.0;
		int volume = 0;
		double pe = 0.0;
		double eps = 0.0;
		double week52low = 0.0;
		double week52high = 0.0;
		double daylow = 0.0;
		double dayhigh = 0.0;
		double movingav50day = 0.0;
		double marketcap = 0.0;
		String name = "";
		String currency = "";
		double shortRatio = 0.0;
		double open = 0.0;
		double previousClose = 0.0;
		String exchange;

		try { 
			
			// Retrieve CSV file for fundementals
			URL yahoo = new URL("http://finance.yahoo.com/d/quotes.csv?s="+ symbol + "&f=l1vr2ejkghm3j3nc4s7pox");
			URLConnection connection = yahoo.openConnection(); 
			InputStreamReader is = new InputStreamReader(connection.getInputStream());
			BufferedReader br = new BufferedReader(is);  
			
			// Parse CSV Into Array
			String line = br.readLine(); 
			//Only split on commas that aren't in quotes
			String[] stockinfo = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			
			price = handleDouble(stockinfo[0]);
			volume = handleInt(stockinfo[1]);
			pe = handleDouble(stockinfo[2]);
			eps = handleDouble(stockinfo[3]);
			week52low = handleDouble(stockinfo[4]);
			week52high = handleDouble(stockinfo[5]);
			daylow = handleDouble(stockinfo[6]);
			dayhigh = handleDouble(stockinfo[7]);   
			movingav50day = handleDouble(stockinfo[8]);
			marketcap = handleDouble(stockinfo[9]);
			name = stockinfo[10].replace("\"", "");
			currency = stockinfo[11].replace("\"", "");
			shortRatio = handleDouble(stockinfo[12]);
			previousClose = handleDouble(stockinfo[13]);
			open = handleDouble(stockinfo[14]);
			exchange = stockinfo[15].replace("\"", "");

			
		} catch (IOException e) {
			Logger log = Logger.getLogger(StockFetcher.class.getName()); 
			log.log(Level.SEVERE, e.toString(), e);
			return null;
		}
		
		return new NormalStock(sym, price, volume, pe, eps, week52low, week52high, daylow, dayhigh, movingav50day, marketcap, name,currency, shortRatio,previousClose,open,exchange);
		
	}

	public static double handleDouble(String x) {
		Double y;
		if (Pattern.matches("N/A", x)) {  
			y = 0.00;   
		} else { 
			y = Double.parseDouble(x);  
		}  
		return y;
	}

	public static int handleInt(String x) {
		int y;
		if (Pattern.matches("N/A", x)) {  
			y = 0;   
		} else { 
			y = Integer.parseInt(x);  
		} 
		return y;
	}

}
