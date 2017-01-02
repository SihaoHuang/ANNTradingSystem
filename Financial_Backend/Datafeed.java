//Uses StockFetcher to obtain fundementals from Yahoo. YahooFinance API is used to obtain historic prices.
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

  public static Double[] getFundementals(String ticker){  // outputs [Pe, Eps, 52high, 52low, Marketcap,ShortRatio]
    Double[] out = new Double[6]; 
    NormalStock st = StockFetcher.getStock(ticker);
    out[0] = st.getPe();
    out[1] = st.getEps();
    out[2] = st.getWeek52high();
    out[3] = st.getWeek52low();
    out[4] = st.getMarketcap();
    out[5] = st.getShortRatio();
    return out;
  }

  public static void main(String[] args){
      System.out.println(getNewestPrice("GOOG"));
      System.out.println(getNewestVolume("GOOG"));
  }

}
