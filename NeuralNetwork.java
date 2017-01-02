//NeuralNetwork.java

class NeuralNetwork{
	private Neuron[][] network;

	public void feedData(double[] example, double target){  //performs forwardprop then backprop
		for (int i = 0; i < example.length; i++){ 
			inputNeuron = network[0][i];
			inputNeuron.setOutput(example[i]);
		}

		for (int layer = 1; layer < network.length; layer++){
			this.fireLayer(layer);
		}
		double output = network[network.length - 1][0].getOutput();
		double error =  output - target;  //order ?

	}

	public void initializeNet(int width, int depth, int outputLayer, int inputLayer){  //intitialize a fully connected array of 
		// intitalize first layer without inputs
		// initialize middle layers
		// initialize output layer of size outputLayer without out nodes
		

		this.connectLayerOut(0)
		for (int layer = 1; layer < network.length - 1 ; layer++){
			this.connectLayerOut(layer);
			this.connectLayerIn(layer);
		}
		this.connectLayerIn(network.length - 1)

	}

	public void fireLayer(int layer){
		for (Neuron neuron : network[layer]){
			neuron.fire();                      //sets output and output'
		}
	}

	public void connectLayerOut(int layer){
		for (Neuron neuron : network[layer]){
			double weight = 1;
			neuron.addOut(network[layer + 1], weight);
		}
	}

	public void connectLayerIn(int layer){
		for (Neuron neuron: network[layer]){
			neuron.addIn(network[layer - 1]);
		}
	}
}