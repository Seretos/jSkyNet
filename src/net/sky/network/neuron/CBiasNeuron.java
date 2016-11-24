package net.sky.network.neuron;

import net.sky.network.CBaseNeuron;

public class CBiasNeuron extends CBaseNeuron{
	public void calculate(float val) {
		if(this.isThreshold()){
			super.setValue(this.getThreshold());
		}else{
			super.setValue(1.0f);
		}
	}
}
