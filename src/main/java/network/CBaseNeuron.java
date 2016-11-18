package main.java.network;

import java.util.ArrayList;
import java.util.List;

public abstract class CBaseNeuron implements INeuron {
	protected List<CSynapse> inputSynapses;
	protected List<CSynapse> outputSynapses;
	protected float value;
	protected float threshold;
	
	abstract public void calculate(float val);
	
	public CBaseNeuron(){
		inputSynapses = new ArrayList<CSynapse>();
		outputSynapses = new ArrayList<CSynapse>();
		value = 0.0f;
		threshold = 0.0f;
	}

	public void setThreshold(float t){
		threshold = t;
	}
	
	public float getThreshold(){
		return threshold;
	}
	
	public void addInputSynapse(CSynapse synapse){
		inputSynapses.add(synapse);
	}
	
	public void addOutputSynapse(CSynapse synapse){
		outputSynapses.add(synapse);
	}
	
	public List<CSynapse> getInputSynapses(){
		return inputSynapses;
	}
	
	public List<CSynapse> getOutputSynapses(){
		return outputSynapses;
	}
	
	public void setValue(float val){
		value = val;
	}
	
	public float getValue(){
		return value;
	}
	
	public void request(){
		for(CSynapse synapse : outputSynapses){
			synapse.setInput(value);
		}
		value = 0.0f;
	}
	
	public void response(){
		float val = 0.0f;
		for(CSynapse synapse : inputSynapses){
			val += synapse.calculate();
		}
		if(val >= threshold){
			this.calculate(val);
		}else{
			this.setValue(0.0f);
		}
	}
	
	public void clear(){
		for(CSynapse synapse : inputSynapses){
			synapse.setInput(0.0f);
		}
	}
}
