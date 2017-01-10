import financialdata.*;
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

		String ticker = args[0];
    Datafeed.loadStocks(); //remember to load stocks whenever Datafeed is used!

    System.out.println(Datafeed.nameFromTicker(ticker));
    System.out.println(Datafeed.sectorFromTicker(ticker));
		Datafeed.printFundementals(ticker);
    System.out.println(Arrays.toString(createInputs(ticker)));

    NeuralNetwork a = new NeuralNetwork();

    a.initializeNet(16,1,1,3);
		System.out.println(a);
		double[] t1 = {0.0,0.0,1.0};
		double y1 = 0.0;
		double[] t2 = {1.0,1.0,1.0};
		double y2 = 1.0;
		double[] t3 = {1.0,0.0,1.0};
		double y3 = 1.0;
		double[] t4 = {0.0,1.0,1.0};
		double y4 = 0.0;

		// Neuron finalNeuron = 
		//System.out.println(a.network[network.length - 1][0]);
		int iters = 1000;
		int t = 300;
		for (int i = 0; i < iters; i++){
			//System.out.println(i);
			a.feedData(t1,y1);
			if (i % t == 0){
				System.out.println(a.toStringOutLast());
				System.out.println(y1);
				System.out.println("--");
			}

			a.feedData(t2,y2);
			if (i % t == 0){
				System.out.println(a.toStringOutLast());
				System.out.println(y2);
				System.out.println("--");
			}

			a.feedData(t3,y3);
			if (i % t == 0){
				System.out.println(a.toStringOutLast());
				System.out.println(y3);
				System.out.println("--");
			}

			a.feedData(t4,y4);
			if (i % t == 0){
				System.out.println(a.toStringOutLast());
				System.out.println(y4);
				System.out.println("============================================");
      }
    }
  }
}
