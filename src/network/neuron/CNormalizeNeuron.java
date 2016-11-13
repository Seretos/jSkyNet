package network.neuron;

import network.CBaseNeuron;

public class CNormalizeNeuron extends CBaseNeuron {

	public void calculate(float val) {
		float multi = 1.0f/(float)Math.sqrt(2*Math.PI);
		float currVal = (float)Math.pow(Math.E, (-Math.pow(val, 2))/2);
		super.setValue(multi*currVal);
	}

}
