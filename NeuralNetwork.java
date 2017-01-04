//NeuralNetwork.java

class NeuralNetwork{
	
	private Neuron[][] network;

	public NeuralNetwork(){
	}

	public static void main(String[] args) {
		NeuralNetwork a = new NeuralNetwork();
		a.initializeNet(16,6,1,8);
		//System.out.println(a.getNeuron(0,0));	
	}
	public void initializeNeurons(int width, int depth, int outputLayer, int inputLayer){
		
		network = new Neuron[depth+2][width];
		network[0] = new Neuron[outputLayer];
		network[network.length-1] = new Neuron[inputLayer];
		
		for (int neuron = 0; neuron < inputLayer; neuron++){  // initialize inputLayer
			System.out.println("allthese");
			Neuron create = new Neuron();
			network[0][neuron] = create;
		}
		for (int layer = 1; layer < depth - 1; layer++){      // initialize all hidden layers
			for (int neuron = 0; neuron < width; neuron++){
				Neuron create = new Neuron();
				network[layer][neuron] = create;
			}
		}
		for (int neuron = 0; neuron < outputLayer; neuron++){ // initialize output layer
			Neuron create = new Neuron();
			network[depth+1][neuron] = create;
		}

	}

	public void feedData(double[] example, double target){  //performs forwardprop then backprop
		for (int i = 0; i < example.length; i++){ 
			// inputNeuron = network[0][i];   
			// inputNeuron.setOutput(example[i]);
			network[0][i].setOutput(example[i]);         // input layer only contains neuron.output
		}

		for (int layer = 1; layer < network.length; layer++){
			this.fireLayer(layer);
		}

		network[network.length - 1][0].backpropagate(target);    // backprop error in output
		for (int layer = network.length -2; layer > 0; layer++){ // bug?
			this.backpropLayer(layer);
		}
		
		for (int layer = 0; layer < network.length - 2; layer++){
			this.changeWeightLayer(layer);
		}

	}

	public void initializeNet(int width, int depth, int outputLayer, int inputLayer){  //intitialize a fully connected array of 
		
		this.initializeNeurons(width, depth, outputLayer, inputLayer);
		System.out.println(network[1].length);
		System.out.println(network.length);
		System.out.println(network[0].length);
		System.out.println(network[network.length-1	].length);

		//	this.connectLayerOut(0);
		System.out.println("got here");
		for (int layer = 1; layer < network.length - 2 ; layer++){
			
			this.connectLayerOut(layer);
			this.connectLayerIn(layer);
			System.out.println("got here too");
		}
		//this.connectLayerIn(network.length - 1);

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
			neuron.adjustWeights(.001);
		}
	}

	public void connectLayerOut(int layer){            
		for (Neuron neuron : network[layer]){
			Double weight = 1.;                              // change weights to random
			neuron.addOut(network[layer + 1], weight);
		}
	}

	public void connectLayerIn(int layer){
		for (Neuron neuron: network[layer]){
			neuron.addIn(network[layer - 1]);
		}
	}
	public Neuron getNeuron(int row, int col){
		return network[row][col];
	}
}
