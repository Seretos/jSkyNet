package network.neuron;

import network.CBaseNeuron;

public class CTangentNeuron extends CBaseNeuron{

	public void calculate(Double val) {
		super.setValue(Math.tanh(val));
	}

}
