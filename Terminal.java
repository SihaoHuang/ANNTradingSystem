import java.io.IOException;
import java.util.Scanner;
import financialdata.*;
import handwritingrecognition.*;

public class Terminal {
    
    private static final String helpText = "You can try the following commands: \n equity<GO> opens an applet to search for S&P 500 component stocks \n";
    public static void main(String[] args){

    System.out.println("Welcome to the neural network price stock prediction system. Type help<GO> for more information. Type exit<GO> to quit or exit current mode.");
    boolean run = true;
    while (run == true){

        Scanner terminalInput = new Scanner(System.in);
        String input = terminalInput.nextLine();

        if (input.equals("exit")) run = false;
        
        if (input.equals("help")) System.out.println(helpText);

        if (input.equals("equity")) {
            // String ticker;
			System.out.println();
            System.out.println("You are now in equity mode. Type list stocks<GO> to see the list of tickers you can inquire about. exit<GO> will exit this mode.");
            terminalInput = new Scanner(System.in);
            input = terminalInput.nextLine();
			Datafeed.loadStocks();
            boolean runApplet = true;
			boolean fund = false;

            while (runApplet == true) {

                if (input.equals("exit")) runApplet = false;

                else if (input.equals("list stocks")) {
					System.out.println(Datafeed.getTickerList());
					terminalInput = new Scanner(System.in);
           		 	input = terminalInput.nextLine();
				}

				else if (input.equals("neural network")) {
                    System.out.println();
                    System.out.println("(WARNING: network make take multiple hours to run)");

                    System.out.println("Input network width");
                    terminalInput = new Scanner(System.in);
                    int width = Integer.parseInt(terminalInput.nextLine());

                    System.out.println("Input network depth");
                    terminalInput = new Scanner(System.in);
                    int depth = Integer.parseInt(terminalInput.nextLine());

                    System.out.println("Input number of iterations");
                    terminalInput = new Scanner(System.in);
                    int iters = Integer.parseInt(terminalInput.nextLine());

                    System.out.println("Initializing network...");

					DriverV2 network = new DriverV2(width,depth,iters);
					network.writeMasterData();
					System.out.println("Training complete.");
				}

                else{
                    // Scanner tickerInput = new Scanner(System.in);
                    // ticker = tickerInput.nextLine();
					if (fund) {
						System.out.println();
						System.out.println("Enter another ticker");
					}
					if (!fund) {
						System.out.println();
						System.out.println(Datafeed.nameFromTicker(input));
						System.out.println(Datafeed.sectorFromTicker(input));
						System.out.println("The newest price is $" + Datafeed.getNewestPrice(input));
						System.out.println();
						System.out.println("Type fundementals to get more information or enter another ticker");
					}
                    terminalInput = new Scanner(System.in);
                    String input2 = terminalInput.nextLine();
					if (input2.equals("exit")) runApplet = false;
                    else if (input2.equals("fundementals")) {
						fund = true;
						System.out.println();
						Datafeed.printFundementals(input); 
					}
                    else input = input2; 
                }
                
            }
        }

        if (input.equals("handwriting recognition")) {
            terminalInput = new Scanner(System.in);
            input = terminalInput.nextLine();
            boolean runApplet = true;
            while (runApplet == true) {
                if (input.equals("exit")) runApplet = false;
            }
        }

        if (input.equals("GUI")) {
            terminalInput = new Scanner(System.in);
            input = terminalInput.nextLine();
            boolean runApplet = true;
            while (runApplet == true) {
                if (input.equals("exit")) runApplet = false;
            }
        }

    }


		// if(ticker.equals("train")){
    //   DriverV2 network = new DriverV2(60,1,100);
		// 	network.writeMasterData();
		// 	System.out.println("got it");
			// System.out.println(Datafeed.getNewestPrice(Datafeed.getTickerList().get(0))/1000); 
			// System.out.println(network.createInputs(Datafeed.getTickerList().get(0)).length);
			// for (double i : network.createInputs(Datafeed.getTickerList().get(0))){
			// 	System.out.println(i);
//			}
			// network.feedAll();
	}
  
  
}
