//javaNN.java

import java.lang.Math;
import java.util.ArrayList;
import java.util.Hashtable; 

class Neuron{
	private double output;
	private double output_prime;
	public double delta;

	private ArrayList<Neuron> inputNeurons = new ArrayList< Neuron > ( );
	private ArrayList<Neuron> outputNeurons = new ArrayList< Neuron > ( );
	 
	private Hashtable<Neuron, Double> outputWeights = new Hashtable<Neuron, Double>();  // Hashtable containing outgoing-weights keyed by their respective neuron
	private double bias;  //unimplemented 

	public Neuron(){
		// bias = Math.random()/14.0 - 1.0/28.0;
		bias = Math.random()/14.0 - 1.0/28.0;
	}

	public void addOut(Neuron[] outNeurons, Double new_weight){    //holds out-weights

		for (Neuron connectNeuron : outNeurons){
			if ( connectNeuron == null ){
				System.out.println("null");
			}
			else{
				Double weight = Math.random() * .02 - .01;  //change weighths here
				outputNeurons.add(connectNeuron);
				outputWeights.put(connectNeuron, weight);
			}
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
		collectSum += bias;  // bias always zero currently
		
		output = sigmoid(collectSum);
		output_prime = sigmoidPrime(collectSum);
	}

	public void adjustWeights(double learn_rate){
		for (Neuron neuron : outputNeurons){
			double calcWeightGrad = neuron.getDelta() * output;
			Double currentWeight = outputWeights.get(neuron);
			Double nextWeight = currentWeight - (learn_rate * calcWeightGrad); 
			outputWeights.put(neuron, nextWeight);
		}
	}
	public void adjustBias(double learn_rate){
		bias -= delta; 
	}
	public void backpropagate(){  // calculate all deltas
		// add errors from layer ahead
		double cumDelta = 0; 
		for (Neuron upperNeuron : outputNeurons){
			cumDelta += this.getWeight(upperNeuron) * upperNeuron.getDelta();
		}	

		this.delta = cumDelta * output_prime; //* output_prime;  //cum weighted delta times output_prime equals delta

	}

	public void backpropagate(double target){  // delta given by test example
		// add errors from layer ahead
		double noGoodName = output - target;   //derivative of cost when C = 1/2 (y - out)^2
		this.delta = noGoodName * output_prime; 
	}

	public double getOutput(){
		return output;
	}
	public void setOutput(double x){
		output = x;
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

