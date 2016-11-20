package net.sky.network.neuron;

import net.sky.network.CBaseNeuron;

public class CBinaryNeuron extends CBaseNeuron {

	public CBinaryNeuron(float t) {
		super();
		setThreshold(t);
	}

	public void calculate(float val) {
		if (super.getThresholdValue(val) > 0.0f) {
			super.setValue(1.0f);
		} else {
			super.setValue(0.0f);
		}
	}

}
