import java.util.ArrayList;
import java.util.Arrays;

public class Driver{

  public static Double[] createInputs(String ticker){
    ArrayList<Double> out = new ArrayList<Double>();
    out.addAll(Datafeed.getFundementals(ticker));
    out.addAll(Datafeed.getPriceSeries(ticker));
    out.addAll(Datafeed.getVolumeSeries(ticker));
    return out.toArray(new Double[out.size()]);
  }

  public static void main(String[] args){
    System.out.println(Arrays.toString(createInputs("AAPL")));
  }
}
