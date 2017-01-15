import financialdata.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DriverV2{

	ArrayList<double[]> masterTraining = new ArrayList<double[]>(); // data is read and stored offline
	ArrayList<Double> masterTarget = new ArrayList<Double>();
  NeuralNetwork nn;
  int iterations = 0;
	
  public DriverV2(int width, int depth, int iters){
    nn = new NeuralNetwork();
    nn.initializeNet(width,depth,1,206); 
    iterations = iters;
    Datafeed.loadStocks(); 
  }

  public double[] createInputs(String ticker){
    ArrayList<Double> out = new ArrayList<Double>();
    out.addAll(Datafeed.getFundementals(ticker));
		out.addAll(Convolutions.gaussianNormalization(Datafeed.getPriceSeries(ticker)));
		out.addAll(Convolutions.gaussianNormalization(Datafeed.getVolumeSeries(ticker)));
		return typeCastDouble(out.toArray(new Double[out.size()]));
  }

	public void writeMasterData(){
		int tickerCount = 0;
		while (tickerCount < Datafeed.getTickerList().size()){ 
			masterTraining.add(createInputs(Datafeed.getTickerList().get(tickerCount)));
			masterTarget.add(Datafeed.getNewestPrice(Datafeed.getTickerList().get(tickerCount))/1000);
			tickerCount ++;
		}	
	}

	public void feedAll(){
		int displayDivisor = 1;
		double cost = 0;
		for (int i = 0; i < iterations; i++){
			int tickerCount = 0;
			while (tickerCount < Datafeed.getTickerList().size()){ //goes through an trains on all stocks in the S&P500 inex
				nn.feedData(masterTraining.get(tickerCount),masterTarget.get(tickerCount));
				cost += Math.abs(Double.parseDouble(nn.toStringOutLast()) - masterTarget.get(tickerCount));
				if (i % displayDivisor == 0){
					System.out.println("Output is: " + nn.toStringOutLast());
					System.out.println("Target is: " + masterTarget.get(tickerCount));
				}
				tickerCount ++;
			}
			System.out.println(i+"th iteration");
			System.out.println("Cost: " + cost);
			System.out.println("--");
			cost = 0;
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
	
	public static void main(String[] args){

		String ticker = args[0];

		if(ticker.equals("train")){
      DriverV2 network = new DriverV2(80,2,10000);
			network.writeMasterData();
			network.feedAll();
		}
  }

}
