import financialdata.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Driver{

  public static double[] createInputs(String ticker){
    ArrayList<Double> out = new ArrayList<Double>();
    out.addAll(Datafeed.getFundementals(ticker));
		out.addAll(Convolutions.gaussianNormalization(Datafeed.getPriceSeries(ticker)));
		out.addAll(Convolutions.gaussianNormalization(Datafeed.getVolumeSeries(ticker)));
		return typeCastDouble(out.toArray(new Double[out.size()]));
  }

  public static void main(String[] args){

		String ticker = args[0];

		if(ticker.equals("train")){
			Datafeed.loadStocks(); 
			feedAll(30,2,1,206);
		}
		
		else{
			Datafeed.loadStocks(); //remember to load stocks whenever Datafeed is used!

			System.out.println(Datafeed.nameFromTicker(ticker));
			System.out.println(Datafeed.sectorFromTicker(ticker));
			Datafeed.printFundementals(ticker);  
		}
  }

	public static void feedAll(int width,int depth,int out,int in){
		NeuralNetwork a = new NeuralNetwork();
		a.initializeNet(30,2,1,206); 
		int iters = 100;
		int displayDivisor = 11;
		double cost = 0;
		for (int i = 0; i < iters; i++){
			int tickerCount = 0;
			while (tickerCount < Datafeed.getTickerList().size()){ //goes through an trains on all stocks in the S&P500 inex
				double[] trainingData = createInputs(Datafeed.getTickerList().get(tickerCount));
				double output = Datafeed.getNewestPrice(Datafeed.getTickerList().get(tickerCount)); //uses newest price as the target value; may need preprocessing
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

	public static double[] typeCastDouble(Double[] in){
		double[] out = new double[in.length];
		int i = 0;
		while(i<in.length){
			out[i] = in[i];
			i++;
		}
		return out;
	}
	
}
