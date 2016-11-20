package net.sky.network.neuron;

import net.sky.network.CBaseNeuron;

public class CBinaryNeuron extends CBaseNeuron {

	public CBinaryNeuron(float t) {
		super();
		setThreshold(t);
	}

	public void calculate(float val) {
		if (val >= threshold) {
			super.setValue(1.0f);
		} else {
			super.setValue(0.0f);
		}
	}

}
