//NeuralNetwork.java

class NeuralNetwork{
	
	private Neuron[][] network;

	public NeuralNetwork(){
	}

	public static void main(String[] args) {
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
		// for (int layer = 1; layer < a.network.length; layer++){
		// 	a.fireLayer(layer);
		// }

		// a.initializeNet();
		//System.out.println(a.toStringOut());
		// a.initializeNet(100,15,60,25);
		
		//System.out.println(a.getNeuron(0,0));	
	}
	public void initializeNeurons(int width, int depth, int outputLayer, int inputLayer){
		
		network = new Neuron[depth+2][width];
		network[0] = new Neuron[inputLayer];
		network[network.length-1] = new Neuron[outputLayer];
		
		for (int neuron = 0; neuron < inputLayer; neuron++){  // initialize inputLayer
			Neuron create = new Neuron();
			network[0][neuron] = create;
		}
		for (int layer = 1; layer < depth + 1; layer++){      // initialize all hidden layers
			for (int neuron = 0; neuron < width; neuron++){
				Neuron create = new Neuron();
				network[layer][neuron] = create;
			}
		}
		for (int neuron = 0; neuron < outputLayer; neuron++){ // initialize output layer
			Neuron create = new Neuron();
			network[network.length - 1][neuron] = create;
		}

	}

	public void feedData(double[] example, double target){  //performs forwardprop then backprop
		for (int i = 0; i < example.length; i++){ 
			// inputNeuron = network[0][i];   
			// inputNeuron.setOutput(example[i]);
			network[0][i].setOutput(example[i]);         // input layer only contains neuron.output
		}
		// int iters = 1000;
		// for (int i = 0; i < iters; i++){
		for (int layer = 1; layer < network.length; layer++){
			this.fireLayer(layer);
		}
		network[network.length - 1][0].backpropagate(target);    // backprop error in output
		for (int layer = network.length - 2; layer >= 0; layer--){ // bug?                      //layer > 1 ?
			this.backpropLayer(layer);
		}
	
		for (int layer = 0; layer < network.length - 1; layer++){
			this.changeWeightLayer(layer);
		}
		// if (i% 250 == 0){
			// System.out.println(this.toStringOut());
		// }
		// }

	}
	public String toStringOutLast(){
		return ""+network[network.length - 1][0].getOutput();
	}
	public String toStringOut(){
		String out = "";
		for (int layer = 0; layer < network.length ; layer++){
			for (Neuron neuron : network[layer]){
				out += "["+Math.round(neuron.getOutput() * 1000)/1000.+"] ";
			}
			out += network[layer].length;
			out += "\n";
		}
		return out;
	}
	public String toString(){
		String out = "";
		for (int layer = 0; layer < network.length ; layer++){
			for (Neuron neuron : network[layer]){
				out += "[ ] ";
			}
			out += network[layer].length;
			out += "\n";
		}
		return out;
		
	}
	public void initializeNet(int width, int depth, int outputLayer, int inputLayer){  //intitialize a fully connected array of 
		
		this.initializeNeurons(width, depth, outputLayer, inputLayer); // initializeNet(10,7,2,5);
		System.out.println(this);	
		System.out.println(network[1].length);
		System.out.println(network.length);
		System.out.println(network[0].length);
		System.out.println(network[network.length-1	].length);

		//System.out.println(network[1].length);
		this.connectLayerOut(0);

		for (int layer = 1; layer < network.length - 1 ; layer++){

			this.connectLayerIn(layer);
			
			this.connectLayerOut(layer);

	

		}
		this.connectLayerIn(network.length - 1);

	}

	public void fireLayer(int layer){
		for (Neuron neuron : network[layer]){
			neuron.fire();                      //sets output and output'
		}
	}
	public void backpropLayer(int layer){
		for (Neuron neuron : network[layer]){
			neuron.backpropagate();
		}
	}

	public void changeWeightLayer(int layer){
		for (Neuron neuron : network[layer]){
			neuron.adjustWeights(.1);
		}
	}

	public void connectLayerOut(int layer){            
		for (Neuron neuron : network[layer]){
			System.out.println(layer + 1);
			Double weight = Math.random() * 2 - 1;                              // change weights to random
			neuron.addOut(network[layer + 1], weight);
		}
	}	

	public void connectLayerIn(int layer){
		for (Neuron neuron: network[layer]){
			System.out.println(layer - 1);
			neuron.addIn(network[layer - 1]);
		}
	}
	public Neuron getNeuron(int row, int col){
		return network[row][col];
	}
}
