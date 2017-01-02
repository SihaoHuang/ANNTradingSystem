//javaNN.java

import java.lang.Math;
import java.util.ArrayList;
import java.util.Hashtable; 

class Neuron{
	public double output;
	private double output_prime;
	public double delta;


	private ArrayList<Neuron> inputNeurons = new ArrayList< Neuron > ( );
	private ArrayList<Neuron> outputNeurons = new ArrayList< Neuron > ( );
	//private Neuron[] inputNeurons;  // implement             ''               'input neuron'
	//private Neuron[] outputNeurons; // implement  <--- array containing every 'output neuron'
	 
	private Hashtable<Neuron, Double> outputWeights = new Hashtable<Neuron, Double>();  // where to intitialize hashtable 'neuron : weight' ?
	private double bias;
	//private Hashtable<Neuron, Double> inputWeights = new Hashtable<Neuron, Double>(); // implement possibly redundant

	public Neuron(){
		bias = .1;
	}


	public void addOut(Neuron[] outNeurons, Double new_weight){    //holds out-weights
		for (Neuron connectNeuron : outNeurons){
			outputNeurons.add(connectNeuron);
			outputWeights.put(connectNeuron, new_weight);
		}

		
	}
	public void addIn(Neuron[] inNeurons){          //holds no weights
		for (Neuron connectNeuron : inNeurons){
			inputNeurons.add(connectNeuron);
		}
	}

	public void fire(){
		double collectSum = 0;
		for (Neuron element : inputNeurons){
			collectSum += ( element.getOutput() * element.getWeight(this) );
		}
		collectSum += bias;
		
		//output = collectSum;
		output = sigmoid(collectSum);
		output_prime = sigmoidPrime(collectSum);
	}

	public void backpropagate(){
		// add errors from layer ahead
		double cumDelta = 0; 
		for (Neuron upperNeuron : outputNeurons){
			cumDelta += this.getWeight(upperNeuron) * upperNeuron.getDelta() * upperNeuron.getOutput_prime();
		}	

		this.delta = cumDelta //* output_prime;  //cum weighted delta times output_prime equals delta

	}

	public void backpropagate(double delta){  // delta given by test example
		// add errors from layer ahead
		double cumDelta = delta; 
		delta = cumDelta;
		delta = cumDelta * 1; // * output_prime; ???
	}

	public static void main(String[] args) {
		 Neuron n0 = new Neuron();
		// System.out.println(sigmoid(0));
		// System.out.println(sigmoidPrime(0));
		Neuron n1 = new Neuron();
		Neuron[] neuronArray = {n1};
		Double twelve = new Double(.2);
		n0.addOut(neuronArray, twelve);
		//System.out.println(n0.getWeight(n1));
		n0.output = .3;
		Neuron[] neuronArray2 = {n0};	
		n1.addIn(neuronArray2);
		n1.fire();
		System.out.println(n1.getOutput());
		System.out.println(n1.getOutput_prime());
		n1.backpropagate(.4);
		System.out.println(n1.getDelta());
		n0.backpropagate();
		System.out.println(n0.getDelta());
	}
	public double getOutput(){
		return output;
	}
	public double getOutput_prime(){
		return output_prime;
	}
	public Double getWeight(Neuron upperNeuron){
		return outputWeights.get(upperNeuron);
	}
	public double getDelta(){
		return delta;
	}

	public static double sigmoid(double x){
		return 1 / ( 1 + Math.exp( - x ) ); // let me know if this spacing makes you cringe... it depends on what screen I use.
	}
	public static double sigmoidPrime(double x){
		return sigmoid(x) * ( 1 - sigmoid(x) );
	}

}

