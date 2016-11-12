package network;

import java.util.ArrayList;
import java.util.List;

public class CTrainingSet {
	private List<Double> inputs;
	private List<Double> outputs;
	
	public CTrainingSet(){
		inputs = new ArrayList<Double>();
		outputs = new ArrayList<Double>();
	}
	
	public CTrainingSet addInput(Double in){
		inputs.add(in);
		return this;
	}
	
	public CTrainingSet addOutput(Double out){
		outputs.add(out);
		return this;
	}
	
	public List<Double> getInputs(){
		return inputs;
	}
	
	public List<Double> getOutputs(){
		return outputs;
	}
}
