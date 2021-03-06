package net.sky.network.neuron;

import net.sky.network.CBaseNeuron;

public class CLogisticNeuron extends CBaseNeuron {

	public void calculate(float val) {
		super.setValue(1.0f / (1.0f + (float) Math.pow(Math.E, -val)));
	}

}
