package network;

import java.util.ArrayList;
import java.util.List;

public abstract class CBaseNeuron implements INeuron {
	protected List<CSynapse> inputSynapses;
	protected List<CSynapse> outputSynapses;
	protected Double value;
	protected Double threshold;
	
	abstract public void calculate(Double val);
	
	public CBaseNeuron(){
		inputSynapses = new ArrayList<CSynapse>();
		outputSynapses = new ArrayList<CSynapse>();
		value = 0.0;
		threshold = 0.0;
	}

	public void setThreshold(Double t){
		threshold = t;
	}
	
	public Double getThreshold(){
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
	
	public void setValue(Double val){
		value = val;
	}
	
	public Double getValue(){
		return value;
	}
	
	public void request(){
		for(CSynapse synapse : outputSynapses){
			synapse.setInput(value);
		}
		value = 0.0;
	}
	
	public void response(){
		Double val = 0.0;
		for(CSynapse synapse : inputSynapses){
			val += synapse.calculate();
		}
		if(val >= threshold){
			this.calculate(val);
		}else{
			this.setValue(0.0);
		}
	}
	
	public void clear(){
		for(CSynapse synapse : inputSynapses){
			synapse.setInput(0.0);
		}
	}
}
