package financialdata;

import java.util.ArrayList;
import java.io.*;
 
//Uses StockFetcher to obtain fundementals from Yahoo. 
//Imports data from s&p500 csv file (for later developmental use)
public class Datafeed{

  static ArrayList<String> tickers = new ArrayList<String>();
  static ArrayList<String> names = new ArrayList<String>();
  static ArrayList<String> sectors = new ArrayList<String>();

  public static void loadStocks(){

    try {
      BufferedReader inputStream = new BufferedReader(new FileReader("financialdata/s&p500.csv"));
      String line = null;
      while((line = inputStream.readLine()) != null) {
        String[] buff = line.split(",");
        tickers.add(buff[0]);
        names.add(buff[1]);
        sectors.add(buff[2]);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public static Double getNewestPrice(String ticker){
    NormalStock st = StockFetcher.getStock(ticker);
    return st.getPrice();
  }

  public static int getNewestVolume(String ticker){
    NormalStock st = StockFetcher.getStock(ticker);
    return st.getVolume();
  }

  public static ArrayList<Double> getFundementals(String ticker){  // outputs [Pe, Eps, 52high, 52low, Marketcap,ShortRatio]
    ArrayList<Double> out = new ArrayList<Double>(); 
    NormalStock st = StockFetcher.getStock(ticker);
    out.add(st.getPe());
    out.add(st.getEps());
    out.add(st.getWeek52high());
    out.add(st.getWeek52low());
    out.add(st.getMarketcap());
    out.add(st.getShortRatio());
    return out;
  }

  public static ArrayList<Double> getPriceSeries(String ticker){
    return GetTimeSeries.getTimeSeries(ticker).get(0);
  }

  public static ArrayList<Double> getVolumeSeries(String ticker){
    return GetTimeSeries.getTimeSeries(ticker).get(1);
  }

  public static ArrayList<String> getTickerList(){
    return tickers;
  }

  public static ArrayList<String> getNameList(){
    return names;
  }

  public static ArrayList<String> getSectorList(){
    return sectors;
  }

  public static String nameFromTicker(String ticker){
    return names.get(tickers.indexOf(ticker));
  }

  public static String sectorFromTicker(String ticker){
    return sectors.get(tickers.indexOf(ticker));
  }

  public static void printFundementals(String ticker){
    NormalStock st = StockFetcher.getStock(ticker);
		System.out.println("Price: " + st.getPrice());
		System.out.println("Volume: " + st.getVolume()); 
		System.out.println("P/E: " + st.getPe());
		System.out.println("EPS: " + st.getEps());
		System.out.println("Year Low: " + st.getWeek52low());
		System.out.println("Year High: " + st.getWeek52high());
		System.out.println("Day Low: " + st.getDaylow());
		System.out.println("Day High: " + st.getDayhigh());
		System.out.println("50 Day Moving Av: " + st.getMovingav50day());
		System.out.println("Market Cap: " + st.getMarketcap());
		System.out.println("The currency is: " + st.getCurrency());
		System.out.println("The short ratio is: " + st.getShortRatio());
		System.out.println("The previous close was: " + st.getPreviousClose());
		System.out.println("The open for today was: " + st.getOpen());
		System.out.println("The exchange is " + st.getExchange());
  }
}
