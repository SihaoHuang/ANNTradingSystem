//javaNN.java

import java.lang.Math;

class Neuron{
	private double output;
	private double output_prime;


	private ArrayList<Neuron> inputNeurons = new ArrayList< Neuron > ( );
	private ArrayList<Neuron> outputNeurons = new ArrayList< Neuron > ( );
	//private Neuron[] inputNeurons;  // implement             ''               'input neuron'
	//private Neuron[] outputNeurons; // implement  <--- array containing every 'output neuron'

	private Map<Neuron, double> outputWeights = new Hashtable<Neuron, double>();  // where to intitialize hashtable 'neuron : weight' ?	
	private Map<Neuron, double> inputWeights = new Hashtable<Neuron, double>(); // implement possibly redundant 
	
	private double bias;

	public Neuron(){
		bias = 1;
	}

	public connectOut(Neuron[] outNeurons){
		for (Neuron connectNeuron : outNeurons){
			outputNeurons.add(connectNeuron);
		}
	}
	public connectIn(Neuron[] inNeurons){
		for (Neuron connectNeuron : inNeurons){
			inputNeurons.add(connectNeuron);
		}	
	}

		//map.put("dog", "type of animal");
	    //String out = map.get("dog");


	public double getOutput(){
		return output;
	}

	public double getOutput_prime(){
		return output_prime;
	}

	public double getWeight(Neuron upperNeuron){
		int index = 0; // locate connecting weight
		return outputWeight[index];
	}

	public static double sigmoid(double x){
		return 1 / ( 1 + Math.exp( - x ) ); // let me know if this spacing makes you cringe... it depends on what screen I use.
	}
	public static double sigmoidPrime(double x){
		return x; // implement
	}

	public void fire(){
		double collectSum = 0;
		for (Neuron element : inputNeurons){
			collectSum += ( element.getOutput() * element.getWeight(this) );
		}

		collectSum += bias;

		output = sigmoid(collectSum);
	}

	public static void main(String[] args) {
		//Neuron n0 = new Neuron()
		System.out.println(sigmoid(0));
	}
}

