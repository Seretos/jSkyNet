package net.sky.network.neuron;

import net.sky.network.CBaseNeuron;

public class CTangentNeuron extends CBaseNeuron {

	public void calculate(float val) {
		super.setValue((float) Math.tanh(val));
	}

}
