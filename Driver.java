import financialdata.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Driver{

  public static Double[] createInputs(String ticker){
    ArrayList<Double> out = new ArrayList<Double>();
    out.addAll(Datafeed.getFundementals(ticker));
		out.addAll(Convolutions.gaussianNormalization(Datafeed.getPriceSeries(ticker)));
		out.addAll(Convolutions.gaussianNormalization(Datafeed.getVolumeSeries(ticker)));
    return out.toArray(new Double[out.size()]);
  }

  public static void main(String[] args){

		String ticker = args[0];
    Datafeed.loadStocks(); //remember to load stocks whenever Datafeed is used!

    System.out.println(Datafeed.nameFromTicker(ticker));
    System.out.println(Datafeed.sectorFromTicker(ticker));
		Datafeed.printFundementals(ticker);
		
    ArrayList<Double> inputs = createInputs(ticker);

		//Feed data 
		
		NeuralNetwork a = new NeuralNetwork();
		a.initializeNet(30,2,1,3);

  
  }
}