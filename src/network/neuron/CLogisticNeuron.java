package network.neuron;

import network.CBaseNeuron;

public class CLogisticNeuron extends CBaseNeuron {

	public void calculate(Double val) {
		super.setValue(1.0/(1.0+Math.pow(Math.E, -val)));
	}
	
}
