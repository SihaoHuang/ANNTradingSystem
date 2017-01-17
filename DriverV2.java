import financialdata.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DriverV2{

	ArrayList<double[]> masterTraining = new ArrayList<double[]>(); // data is read and stored offline
	ArrayList<Double> masterTarget = new ArrayList<Double>();

	ArrayList<double[]> masterTestData = new ArrayList<double[]>();
	ArrayList<Double> masterTestTarget = new ArrayList<Double>();
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
		while (tickerCount < 400){ 
			masterTraining.add(createInputs(Datafeed.getTickerList().get(tickerCount)));
			masterTarget.add(Datafeed.getNewestPrice(Datafeed.getTickerList().get(tickerCount))/1000);
			tickerCount ++;
		}	
	}

	public void writeTestData(){
		int tickerCount = 400;
		while (tickerCount < Datafeed.getTickerList().size()){ 
			masterTestData.add(createInputs(Datafeed.getTickerList().get(tickerCount)));
			masterTestTarget.add(Datafeed.getNewestPrice(Datafeed.getTickerList().get(tickerCount))/1000);
			tickerCount ++;
		}	
	}

	public void feedAll(){
		int displayDivisor = 1;
		double[] costHistory = new double[iterations];
		double[] accuracyInHistory = new double[iterations];
		double[] accuracyOutHistory = new double[iterations];
		double cost = 0.0;
		int numCorrectInSample=0;
		int numCorrectOutSample=0;
		int numTotal=0;
		double accuracyInSample = 0.0;
		double accuracyOutSample = 0.0;

		for (int i = 0; i < iterations; i++){
			int tickerCount = 0;
			while (tickerCount < Datafeed.getTickerList().size()){ //goes through an trains on all stocks in the S&P500 inex
				double[] target = new double[] {masterTarget.get(tickerCount) + .5};

				nn.feedData(masterTraining.get(tickerCount),target);
				double signHypothesis = Math.signum(Double.parseDouble(nn.toStringOutLast())-.51); //prints hypothesis Y
				System.out.println("~~~~");
				System.out.println(signHypothesis);
				if (signHypothesis == Math.signum(target[0]-.5)){
					numCorrectInSample += 1;
				}
				numTotal +=1;
				System.out.println("~~~~");
				cost += Math.abs(Double.parseDouble(nn.toStringOutLast()) - target[0]);
				
				if (i % displayDivisor == 0){
					System.out.println("Output is: " + nn.toStringOutLast());
					System.out.println("Target is: " + (masterTarget.get(tickerCount)+0.5));
				}
				tickerCount ++;
				

				double[] targetO = new double[] {masterTestTarget.get(tickerCount)+ .5}; // this tests model on out of sample masterTestTarget and MasterTestData 
				if (nn.feedDataTest(masterTestData.get(tickerCount),targetO)){
					numCorrectOutSample += 1;
				}
			}
			costHistory[i] = cost; // records epochs cost
			accuracyInHistory[i] = numCorrectInSample * 1.0 / numTotal; // returns double %accuracy
			accuracyOutHistory[i] =  numCorrectOutSample * 1.0 / numTotal;// reut
			System.out.println(i+"th iteration");
			System.out.println("Cost: " + cost);
			System.out.println("--");
			cost = 0;
		}
		System.out.println("Cost History:");                 // display cost and accuracy arrays
		for (double dub : costHistory){
			System.out.println(dub);
		}
		System.out.println("Accuracy-In-Sample History:");
		for (double dub : accuracyInHistory){
			System.out.println(dub);
		}
		System.out.println("Accuracy-Out-of-Sample History:");
		for (double dub : accuracyOutHistory){
			System.out.println(dub);
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

	public String giveRecommendation(String ticker){
		//check if valid ticker
		double[] stockdata; // fetches stock data
		double prediction = nn.feedDataAsk(stockdata);
		String action = "";
		if (prediction > 0){
			action = "BUY";
		}
		else{
			action = "SELL";
		}
		return "Our recommendation for "+ ticker + " is to " + action;
	}
	
	public static void main(String[] args){

		String ticker = args[0];

		if(ticker.equals("train")){
      DriverV2 network = new DriverV2(60,1,100);
			network.writeMasterData();
			//network.writeTestData();             // here write master test data pls

			network.feedAll();
		}
  }

}
