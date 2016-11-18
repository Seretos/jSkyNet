package main.java.network.neuron;

import main.java.network.CBaseNeuron;

public class CLogisticNeuron extends CBaseNeuron {

	public void calculate(float val) {
		super.setValue(1.0f/(1.0f+(float)Math.pow(Math.E, -val)));
	}
	
}
