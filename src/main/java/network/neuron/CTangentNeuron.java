package main.java.network.neuron;

import main.java.network.CBaseNeuron;

public class CTangentNeuron extends CBaseNeuron{

	public void calculate(float val) {
		super.setValue((float)Math.tanh(val));
	}

}
