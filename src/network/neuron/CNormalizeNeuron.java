package network.neuron;

import network.CBaseNeuron;

public class CNormalizeNeuron extends CBaseNeuron {

	public void calculate(Double val) {
		Double multi = 1.0/Math.sqrt(2*Math.PI);
		Double currVal = Math.pow(Math.E, (-Math.pow(val, 2))/2);
		super.setValue(multi*currVal);
	}

}
