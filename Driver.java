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
		feedAll();
  }

	public static void feedAll(){
		NeuralNetwork a = new NeuralNetwork();
		a.initializeNet(30,2,1,206);
		int iters = 10000;
		int displayDivisor = 1100;
		double cost = 0;
		for (int i = 0; i < iters; i++){
			int tickerCount;
			while (tickerCount < Datafeed.getTickerList().size()){ //goes through an trains on all stocks in the S&P500 inex
				Double[] trainingData = createInputs(Datafeed.getTickerList().get(tickerCount));
				Double output = Datafeed.getNewestPrice(); //uses newest price as the target value; may need preprocessing
				a.feedData(trainingData,output);
				if (i % displayDivisor == 0){
					System.out.println(i+"th iteration");
					System.out.println(a.toStringOutLast());
					System.out.println("--");
					cost += Math.abs(Double.parseDouble(a.toStringOutLast()) - output);
				}
			}
		}
	}
}
