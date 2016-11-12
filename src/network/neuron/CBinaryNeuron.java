package network.neuron;

import network.CBaseNeuron;

public class CBinaryNeuron extends CBaseNeuron{

	public CBinaryNeuron(Double t){
		super();
		setThreshold(t);
	}
	
	public void calculate(Double val) {
		if(val >= threshold){
			super.setValue(1.0);
		}/*else if(val <= -1.0){
			super.setValue(-1.0);
		}*/else{
			super.setValue(0.0);
		}
	}

}
