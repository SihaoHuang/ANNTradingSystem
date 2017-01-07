import java.util.ArrayList;

//Uses StockFetcher to obtain fundementals from Yahoo. 
//Imports data from s&p500 csv file (for later developmental use)
public class Datafeed{

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

}
