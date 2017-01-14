//NeuralNetwork.java

import java.util.ArrayList;

class NeuralNetwork{
	
	private Neuron[][] network;

	public NeuralNetwork(){
	}

	public static void main(String[] args) {                 
		NeuralNetwork a = new NeuralNetwork();
		a.initializeNet(30,2,10,784);

		HData handData = new HData();
		handData.loadData();           // load up handwritten dataset 

		int numIters = 100;
		for (int i = 0; i < numIters; i++){        // every iteration: feed every data example loaded 
			a.feedDataExamples(handData.dataExamples);  //handData.dataExamples is an ArrayList with double[][] example entries (each example contains 2 double[] arrays)
		}


	}


	// feedDataExamples takes an ArrayList [ {data-array, label-array},{data-array, label-array}...]
	public void feedDataExamples(ArrayList<double[][]> data){ 
		int first = 0;
		for (double[][] example : data){
			this.feedData(example[0],example[1]);
			if (first == 0){ 
			System.out.println("test:" + this.toStringOutLast());
			String out = "";
			for (double i : example[1]){
				out += "[" + i + ",] "; 
			}
			System.out.println("label:" + out);
			}
		}
	}
	public void feedData(double[] example, double[] targets){  //performs forwardprop then backprop
		
		for (int i = 0; i < example.length; i++){ //set neuron.output to example[@postion] (inputs) 
			network[0][i].setOutput(example[i]);  // input neurons only contain attribute neuron.output
		}

		for (int layer = 1; layer < network.length; layer++){ // fire every layer except 0th
			this.fireLayer(layer);
		}
		for (int input = 0; input < targets.length; input++){ // tell every output neuron its error
			network[network.length - 1][input].backpropagate(targets[input]);
		}
		for (int layer = network.length - 2; layer >= 0; layer--){ // caluculate deltas for remaining layers
			this.backpropLayer(layer);
		}
	
		for (int layer = 0; layer < network.length - 1; layer++){ // update outputting-weights for every layer except last
			this.changeWeightLayer(layer);
		}
		// if (i% 250 == 0){
			// System.out.println(this.toStringOut());
		// }
	}
	public String toStringOutLast(){ // return readable list of output layer's outputs
		String out = "";
		for (Neuron neuron : network[network.length-1]){
			out += "["+Math.round(neuron.getOutput() * 100)/100.+"] ";
		}
		return out;
	}
	public String toStringOut(){   // returns array of entire 2-d network, with output shown in each neuron
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
	public String toString(){  //return structured 2-d array of "blank" neruons
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
	public void initializeNeurons(int width, int depth, int outputLayer, int inputLayer){ // instantiates all neurons from dims
		
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

	public void initializeNet(int width, int depth, int outputLayer, int inputLayer){  //intitialize a fully connected array of 
		
		this.initializeNeurons(width, depth, outputLayer, inputLayer); // initializeNet(10,7,2,5);

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
			neuron.backpropagate();             //sets deltas
		}
	}

	public void changeWeightLayer(int layer){
		for (Neuron neuron : network[layer]){
			neuron.adjustWeights(.1);                /// here adjust learning rate
		}
	}

	public void connectLayerOut(int layer){            
		for (Neuron neuron : network[layer]){

			Double weight = Math.random() - 0.5 ;      //sets weights to random value (-0.5, 0.5)                        
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
