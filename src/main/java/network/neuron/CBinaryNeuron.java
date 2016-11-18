package main.java.network.neuron;

import main.java.network.CBaseNeuron;

public class CBinaryNeuron extends CBaseNeuron{

	public CBinaryNeuron(float t){
		super();
		setThreshold(t);
	}
	
	public void calculate(float val) {
		if(val >= threshold){
			super.setValue(1.0f);
		}/*else if(val <= -1.0){
			super.setValue(-1.0);
		}*/else{
			super.setValue(0.0f);
		}
	}

}
